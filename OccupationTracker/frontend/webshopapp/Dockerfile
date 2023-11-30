# Dockerfile-frontend
FROM node:18
WORKDIR /app
COPY  . .

RUN npm install
RUN npm install -g @angular/cli@12.2.1
EXPOSE 4200
CMD ["ng", "serve", "--host", "0.0.0.0"]

