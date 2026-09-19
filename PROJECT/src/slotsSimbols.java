import java.util.Random;

public class slotsSimbols {
static Random random = new Random();
private char simbol;

    public slotsSimbols() {
        this.simbol = generateSimbol();
    }
private char generateSimbol(){
return simbols();
}
private char simbols(){
char simbol = ' ';
double numb = 0;
while(simbol == ' ') {

    numb = random.nextDouble(1,17.4);

    if (numb > 0 && numb < 3.4) {
    simbol = '&';
    }
    else if (numb > 3.4 && numb < 6.4) {
        simbol = '@';
    }
    else if (numb > 6.4 && numb < 9.4) {
        simbol = '#';
    }
    else if (numb > 12.4 && numb < 13.4) {
        simbol = '$';
    }
    else if (numb > 15.4 && numb < 16) {
        simbol = '7';
    }
}
    return simbol;
}

public char getSimbol() {
        return simbol;
    }


}
