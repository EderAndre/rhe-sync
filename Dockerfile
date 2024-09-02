FROM openjdk:21-ea-jdk-oraclelinux8

WORKDIR	/app

COPY ./build/libs/rhe-sync-0.1-all.jar .

# mkdir -m 777 /app/files/sended /app/files/waiting /app/files/rsync 
# mkdir -m 777 /app/files/normalized /app/files/aborted /app/files/processed

ENTRYPOINT ["java", "-jar" , "rhe-sync-0.1-all.jar", "-Xmx2048m"]
