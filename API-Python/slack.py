from http import client
from slack_sdk import WebClient

def chamadoSlack(msg):
    client = WebClient('xoxb-3943229718052-4265288782305-9dSaEYmNz1qnGFyYatvfeviZ')
    response = client.chat_postMessage(channel = 'C047EKLFEAD', text= msg)

