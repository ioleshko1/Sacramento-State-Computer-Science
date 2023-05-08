from socket import *

# Use the same serverPort as the one in the client
serverPort = 12111

# Create a server socket
serverSocket = socket(AF_INET, SOCK_DGRAM)

# Bind the serverSocket with the serverPort number
serverSocket.bind(('', serverPort))

# Print the message to display that the server is ready
print('The server is ready to receive')

while 1:
    
    # Recieve the encoded message and the clientAddress from the client
    message, clientAddress = serverSocket.recvfrom(2048)
    
    # Modify the message that we recieved
    modifiedMessage = message.lower()
    
    # Sent the modifiedMessage to the clientAddress that was given to us
    serverSocket.sendto(modifiedMessage, clientAddress)
    
    