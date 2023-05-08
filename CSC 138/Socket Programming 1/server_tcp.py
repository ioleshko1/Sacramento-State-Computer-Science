from socket import *

# Use the same serverPort as the one in the client
serverPort = 12113

# Create a server socket
serverSocket = socket(AF_INET,SOCK_STREAM)

# Bind the serverSocket with the serverPort number
serverSocket.bind(('',serverPort))

# Listen to the serverSocket for 1
serverSocket.listen(1)

# Print the message to display that the server is ready
print('The server is ready to receive')

while 1:
    
    # Accept the connectionSocket and the address from the client
    connectionSocket, addr = serverSocket.accept()
    
    # Recieve the encoded sentence
    sentence = connectionSocket.recv(1024)
    
    # Change the sentence to uppercase letters
    capitalizedSentence = sentence.upper()
    
    # Sent the modified sentence back to the client
    connectionSocket.send(capitalizedSentence)
    
    # Close the connection
    connectionSocket.close()
    
    