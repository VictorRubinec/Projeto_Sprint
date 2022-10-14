from http import client
from slack_sdk import WebClient

def chamadoSlack(tipo):
    if(tipo == 'cpu'):
        client = WebClient('xoxb-3943229718052-4210433500055-kxgb5MmwdBTH4NEegd7XQv23')
        response = client.chat_postMessage(channel = 'C03UCELSTPA', text= 'chamado teste')

x = 'cpu'
chamadoSlack(x)