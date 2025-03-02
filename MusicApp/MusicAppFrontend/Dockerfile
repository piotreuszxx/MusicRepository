FROM nginx

LABEL authors="ADMIN"

WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

COPY nginx.conf /etc/nginx/conf.d/default.conf

COPY dist/music-app-frontend/browser .

EXPOSE 4200

CMD nginx -g 'daemon off;'

