const int In1 = 6;
const int In2 = 7;

const int In3 = 8;
const int In4 = 9;

const int ENA = 5;
const int ENB = 10;
const int SPEED = 255;

void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
  pinMode(In1,OUTPUT);
  pinMode(In2,OUTPUT);

  pinMode(In3,OUTPUT);
  pinMode(In4,OUTPUT);
  
  pinMode(ENA,OUTPUT);
  analogWrite(ENA, SPEED);

  pinMode(ENB,OUTPUT);
  analogWrite(ENB, SPEED);
  
}

void forward(){
    digitalWrite(In1,HIGH);
    digitalWrite(In2,LOW);

    digitalWrite(In3,HIGH);
    digitalWrite(In4,LOW);
}


void backward(){
    digitalWrite(In1,LOW);
    digitalWrite(In2,HIGH);

    digitalWrite(In3,LOW);
    digitalWrite(In4,HIGH);
}


void right(){
    digitalWrite(In1,HIGH);
    digitalWrite(In2,LOW);

    digitalWrite(In3,LOW);
    digitalWrite(In4,LOW);
}


void left(){
    digitalWrite(In1,LOW);
    digitalWrite(In2,LOW);

    digitalWrite(In3,HIGH);
    digitalWrite(In4,LOW);
}

void stop(){
    digitalWrite(In1,LOW);
    digitalWrite(In2,LOW);

    digitalWrite(In3,LOW);
    digitalWrite(In4,LOW);
}

char fb = ' ';
char rl = ' ';
void loop() {
  // put your main code here, to run repeatedly:
  char read = ' ';
  if (Serial.available() > 0) {
    read = Serial.read();
    Serial.println(read);
  }
  if(read == 'f'){
      forward();
      fb = read;
  }else if(read == 'b'){
      backward();
      fb = read;
  }else if(read == 'r'){
      right();
      rl = read;
  }else if(read == 'l'){
      left();
      rl = read;
  }else if(read == 's'){
      stop();
  }
}


