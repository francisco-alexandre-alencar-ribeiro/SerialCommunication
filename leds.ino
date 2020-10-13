void setup() {
    Serial.begin(9600);
    pinMode(4, OUTPUT);
    pinMode(5, OUTPUT);
}

void loop() {
    if(Serial.available()){
        int ledData = Serial.read() - '0';
        if(ledData != 1) {
            lowLeds();
            digitalWrite(ledData, HIGH); 
        } else {
            highLeds();
        }
    }
}

void lowLeds() {
  for(int led = 4; led <= 5; led++)
      digitalWrite(led, LOW);
}

void highLeds() {
  for(int led = 4; led <= 5; led++)
      digitalWrite(led, HIGH);
}
