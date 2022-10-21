from http import client
from slack_sdk import WebClient

def chamadoSlack(msg, nome):
    client = WebClient('xoxb-3943229718052-4265288782305-v3XhL7GlCGqD0SeSxibyjHek')
    response = client.chat_postMessage(channel = 'C0479Q92M4N', text= msg, username= nome)

