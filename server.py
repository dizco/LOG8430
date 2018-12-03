from flask import Flask, jsonify

app = Flask(__name__)

LOCALHOST_URL = '127.0.0.1'

client = MongoClient("mongodb://" + LOCALHOST_URL + ":27017/store" )
db = client['shop'] 

spark = SparkSession \
            .builder \
            .appName("log8430") \
            .master("local") \
            .config("spark.mongodb.input.uri", "mongodb://" + LOCALHOST_URL + "/shop.receipts") \
            .config("spark.jars.packages", "org.mongodb.spark:mongo-spark-connector_2.11:2.2.5") \
            .getOrCreate()

@app.route('/')
def index():
    return "Hello, World!"

@app.route('/receipts', methods=['POST'])
def create_receipt():
    if not request.json or not 'items' in request.json:
        abort(400)
    db.receipts.insert_one(request.json)
    return jsonify(), 201


@app.route('/receipts', methods=['GET'])
def get_receipts():
    df = spark.read.format("com.mongodb.spark.sql.DefaultSource") \
        .load()
    transaction = df.groupBy("_id") \
            .agg(PysparkF.collect_list("items.name").alias("itemName")) \
            .rdd \
            .flatMap(lambda x: x.itemName)
    transaction.collect()

    model = FPGrowth.train(transaction, minSupport=0.2, numPartitions=10)
    result = model.freqItemsets().collect()

    return jsonify({'receipts': result})

if __name__ == '__main__':
    app.run(debug=True)