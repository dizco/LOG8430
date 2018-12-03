from flask import Flask, jsonify

app = Flask(__name__)

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
    trans = df.groupBy("_id") \
            .agg(PysparkF.collect_list("items.name").alias("itemName")) \
            .rdd \
            .flatMap(lambda x: x.itemName)
    trans.collect()

    model = FPGrowth.train(trans, minSupport=0.2, numPartitions=10)
    result = model.freqItemsets().collect()

    return jsonify({'receipts': result})


if __name__ == '__main__':
    app.run(debug=True)