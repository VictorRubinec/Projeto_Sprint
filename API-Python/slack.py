from http import client
from slack_sdk import WebClient

def chamadoSlack(msg):
    client = WebClient('xoxb-3943229718052-4239976660435-rx1aOWCuE1uNYhNoc05sQpXN')
    response = client.chat_postMessage(channel = 'C047EKLFEAD', text= msg)

