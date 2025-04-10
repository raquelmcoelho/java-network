# a multistage ExpressJS Dockerfile example
######### BASE STAGE

FROM node:23.3.0-alpine AS base
LABEL authors="ZeWunderfulDev"

RUN apk -U upgrade

######### BUILD STAGE
# the corresponding image may take 500+ MB
FROM base AS build

RUN apk add git

WORKDIR /home/node
#COPY "package*.json" ./
RUN apk add --update --no-cache python3 build-base gcc && ln -sf /usr/bin/python3 /usr/bin/python
RUN npm install -g npm@10.9.0
RUN git clone https://github.com/ZeWunderfulDev/MyAppRepository.git
WORKDIR /home/node/MyAppBack
RUN npm install --production

######### PRODUCTION STAGE
# the final image is probably less than 250 MB

FROM base AS prod

RUN apk add curl

COPY --from=build /home/node/MyAppBack /home/node/MyAppBack
WORKDIR /home/node/MyAppBack
RUN chown -R node /home/node/MyAppBack
USER node

EXPOSE 3000
# Environnement variables
ENV DEBUG=myapp:*
ENV NODE_ENV=production
ENV DOCKER=true
ENV HTTP=true

CMD ["node", "bin/www"]
