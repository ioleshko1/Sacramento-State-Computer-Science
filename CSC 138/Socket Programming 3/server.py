# Import the socket module
from socket import *

# Import sys in order to terminate the program
import sys

# Create a server socket
serverSocket = socket(AF_INET, SOCK_STREAM)

# Use 9912 as the serverPort because that is used for web servers
serverPort = 9912

# Bind the serverSocket with the serverPort number
serverSocket.bind(('',serverPort))

# Listen to the serverSocket for 1
serverSocket.listen(1)

# Print the message to display that the server is ready
print('The server is ready to receive')


while True:
    
    #Establish the connection
    print('Ready to serve...')
    
    # Accept the connectionSocket and the address from the client
    connectionSocket, addr = serverSocket.accept()
    
    try:
        # Recieve the encoded message
        message = connectionSocket.recv(1024).decode()
        
        # Capture the filename by splitting out the 2nd part of the message
        filename = message.split()[1]
        
        # Open the file
        f = open(filename[1:])
        
        # Capture the data that was in the file in the 2nd part by reading the file
        outputdata = f.read()
        
        # Create a variable to hold the OK command
        okCommand = 'HTTP/1.1 200 OK\r\n\r\n'
        
        # Send one HTTP header line into socket
        connectionSocket.send(okCommand.encode())
        
        # Send the content of the requested file to the client        
        for i in range(0, len(outputdata)):
            
            # Loop through each of the lines of code and send it
            connectionSocket.send(outputdata[i].encode())
        
        # Send the return carriage to show that we are ending our message
        connectionSocket.send("\r\n".encode())
        
        # Close client socket
        connectionSocket.close()
            
    except IOError:
        
        # Create a variable to hold the NOT FOUND command
        notFoundCommand = 'HTTP/1.1 404 Not Found\r\n\r\n'
        
        # Send response message for file not found
        connectionSocket.send(notFoundCommand.encode())

        # Display the HTML message that a file was not found
        connectionSocket.send("<h1>404 Not Found</h1>\r\n".encode())

        # Close client socket
        connectionSocket.close()
        
# Close server socket
serverSocket.close()

# Terminate the program after sending the corresponding data
sys.exit()
