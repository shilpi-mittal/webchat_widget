FROM openjdk:17.0.2-jdk

ARG CHROME_DRIVER_VERSION=103.0.5060.53

RUN microdnf install -y unzip \
	&& curl -s -o /tmp/chromedriver.zip https://chromedriver.storage.googleapis.com/$CHROME_DRIVER_VERSION/chromedriver_linux64.zip \
	&& unzip /tmp/chromedriver.zip -d /opt \
	&& rm /tmp/chromedriver.zip \
	&& mv /opt/chromedriver /opt/chromedriver-$CHROME_DRIVER_VERSION \
	&& chmod 755 /opt/chromedriver-$CHROME_DRIVER_VERSION \
	&& ln -s /opt/chromedriver-$CHROME_DRIVER_VERSION /usr/bin/chromedriver

COPY . .

RUN ./gradlew build
