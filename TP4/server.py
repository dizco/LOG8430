from flask import Flask, abort, request, jsonify
import pyspark
from pyspark.mllib.fpm import FPGrowth
from pyspark.sql import SparkSession, functions as PysparkFunctions
from pymongo import MongoClient

app = Flask(__name__)

LOCALHOST_URL = '127.0.0.1'

client = MongoClient("mongodb://" + LOCALHOST_URL + ":27017/shop" )
db = client['shop'] 

'''
db.receipts.insert_one({
    "items": [
        {"name": "Carotte", "Price": 3.0},
        {"name": "Epice", "Price": 5.0}
    ]
})
'''

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

    response = []

    if (df.count() != 0):
        transaction = df.groupBy("_id") \
            .agg(PysparkFunctions.collect_list("items.name").alias("itemName")) \
            .rdd \
            .flatMap(lambda x: x.itemName)
        transaction.collect()

        model = FPGrowth.train(transaction, minSupport=0.2, numPartitions=10)
        result = model.freqItemsets().collect()

        for r in result:
            response.append({'item': r.items, 'freq': r.freq})

    return jsonify(response)

if __name__ == '__main__':
    app.run(debug=True)
