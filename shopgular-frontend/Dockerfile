FROM node:16.18-alpine AS builder
WORKDIR /tmp/shopgular-frontend/
COPY . /tmp/shopgular-frontend/
RUN yarn set version stable && echo "nodeLinker: node-modules" >> /tmp/shopgular-frontend/.yarnrc.yml && yarn install && yarn build

FROM nginx:alpine
COPY nginx.conf /etc/nginx/nginx.conf
COPY --from=builder /tmp/shopgular-frontend/dist/shopgular /usr/share/nginx/html
