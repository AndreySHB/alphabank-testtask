FROM openjdk:11

EXPOSE 8080

RUN mkdir ./myapp

COPY ./alphabank-testtask.jar ./myapp

CMD java -jar ./myapp/alphabank-testtask.jar