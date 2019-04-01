from websocket import create_connection

uri = 'ws://localhost:8181/core'

def publish_mycroft_message(type, data):
    ws = create_connection(uri)
    message = '{"type": "' + type + '", "data": ' + data +'}'
    print(message)
    ws.send(message)
    ws.close()