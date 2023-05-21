# Use an official Node.js runtime as the base image
FROM node:16.15.1

# Set the working directory inside the container
WORKDIR '/appbank'

# Copy package.json and package-lock.json to the working directory
COPY package.json .

# Install dependencies
RUN npm install

# Copy the entire project to the working directory
COPY . .

#Opening port 3000
EXPOSE 3000
# Set the command to start the application
CMD [ "npm", "start" ]
