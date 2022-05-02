import javadraw.*;
import java.util.*;

public class wordle extends Window {

    ArrayList<Rectangle> list = new ArrayList<Rectangle>();
    ArrayList<Text> list2 = new ArrayList<Text>();
    ArrayList<Rectangle> list3 = new ArrayList<Rectangle>();
    ArrayList<Oval> rain;
    boolean reading;
    String word;
    String word2;
    String testedword;
    Text input;
    Text wordle;
    Rectangle shape3;
    int row = 0;
    boolean starting;
    boolean guessing;
    int letterx = 300;
    int lettery = 125;
    int counter = 1;
    int counter2 = 0;
    int boxhelper = 0;
    String helperword = "";
    Color betterpink = new Color(255, 0, 196);
    Rectangle back;
    Color rainColor = new Color(Color.BLUE);
    boolean playing;
    int checker = 0;

    public static void main(String[] args) {
        Window.open();
    }

    public void start() {

        screen.color(betterpink);
        base();
        reading = true;
        starting = true;
        //word = "";
        testedword = "";
        rain = new ArrayList<Oval>();
        rainColor = (Color.BLUE);
        playing = true;

        shape3 = new Rectangle(screen, 200, 450, 400, 100, Color.WHITE);
        Rectangle shape4 = new Rectangle(screen, 210, 460, 380, 80, Color.BLACK);
        
        Text backspace = new Text(screen, "(LEFT to delete)", 400, 425, Color.WHITE);
        backspace.bold(true);
        backspace.size(20);
        backspace.center(400, 425);

        Text info = new Text(screen, "Enter your starting 5 letter word here:", 400, 475, Color.WHITE);
        info.bold(true);
        info.size(20);
        info.center(400, 475);

        input = new Text(screen, "", 370, 500, Color.GREEN);
        input.bold(true);
        input.size(20);
        
        back = new Rectangle(screen, 10, 60, 130, 230, Color.BLACK);

        Rectangle blue = new Rectangle(screen, 50, 100, 50, 25, Color.BLUE);

        Rectangle green = new Rectangle(screen, 50, 140, 50, 25, Color.CYAN);

        Rectangle red = new Rectangle(screen, 50, 180, 50, 25, Color.RED);

        Rectangle yellow = new Rectangle(screen, 50, 220, 50, 25, Color.YELLOW);

        Rectangle pink = new Rectangle(screen, 50, 260, 50, 25, betterpink);

        Text colorInfo = new Text(screen, "change color: ", 50, 50, Color.WHITE);
        colorInfo.size(18);
        colorInfo.bold(true);
        colorInfo.center(75, 75);
        

        Text counter = new Text(screen, "Counter: " + rain.size(), 610, 550, Color.WHITE);
        while (true) {
            screen.update();
            screen.sleep(1 / 30.0);
            
            addRain();
            rainFall();
            counter.text("Counter: " + rain.size());
            deleteRain();

            if (testedword.length() == 5) {

                shape3.remove();
                shape4.remove();
                info.remove();
                input.remove();

                word = testedword;
                word2 = word;

                starting = false;
                guessing = true;
                row = 1;
                guess();

            }
            
            if(!playing) {
            
            back.remove();
            blue.remove();
            green.remove();
            red.remove();
            yellow.remove();
            pink.remove();
            colorInfo.remove();
            
        }
            
            

        }

    }

    public void base() {

        wordle = new Text(screen, "WORDLE", 400, 100);
        wordle.color(Color.WHITE);
        wordle.bold(true);
        wordle.size(40);
        wordle.center(400, 50);

        int counter = 0;
        int helper = 0;
        int squarex = 300;
        int squarey = 125;

        while (counter < 30) {

            helper++;

            Rectangle square = new Rectangle(screen, squarex, squarey, 40, 40, Color.WHITE);
            square.center(squarex, squarey);
            list3.add(square);

            Rectangle square2 = new Rectangle(screen, squarex, squarey, 30, 30, Color.BLACK);
            square2.center(squarex, squarey);
            list.add(square2);

            if (helper == 5) {

                squarey += 50;
                squarex = 300;
                helper = 0;

            } else {

                squarex += 50;

            }
            counter++;

        }

        

    }

    public void guess() {

        if (list2.size() == counter) {

            letterx += 50;
            counter++;

        }

    }

    public void check() {

        helperword = "";
        int morethanone = 0;
        checker = 0;

        while (counter2 < list2.size()) {

            String helper = "";
            helper += word.charAt(counter2);

            if (helper.equals(list2.get(counter2).text()) && !helperword.contains(list2.get(counter2).text())) {

                colorChange(boxhelper, 1);
                helperword += list2.get(counter2).text();

                System.out.println("green: " + list2.get(counter2).text() + " helperword: " + helperword);

            } else if (helper.equals(list2.get(counter2).text()) && moreThanOnce(word, list2.get(counter2).text()) && morethanone == 0) {

                colorChange(boxhelper, 1);
                helperword += list2.get(counter2).text();
                morethanone++;

                System.out.println("green: " + list2.get(counter2).text() + " helperword: " + helperword);

            } else if (helper.equals(list2.get(counter2).text()) && moreThanTwice(word, list2.get(counter2).text()) && morethanone == 1) {

                colorChange(boxhelper, 1);
                helperword += list2.get(counter2).text();
                morethanone++;

            } else if (word.contains(list2.get(counter2).text()) && !helperword.contains(list2.get(counter2).text())) {

                colorChange(boxhelper, 2);
                helperword += list2.get(counter2).text();

                System.out.println("yellow: " + list2.get(counter2).text() + " helperword: " + helperword);

            } else if (word.contains(list2.get(counter2).text()) && moreThanOnce(word, list2.get(counter2).text()) && morethanone == 0) {

                colorChange(boxhelper, 2);
                helperword += list2.get(counter2).text();
                morethanone++;

                System.out.println("yellow: " + list2.get(counter2).text() + " helperword: " + helperword);

            } else if (word.contains(list2.get(counter2).text()) && moreThanTwice(word, list2.get(counter2).text()) && morethanone == 1) {

                colorChange(boxhelper, 1);
                helperword += list2.get(counter2).text();
                morethanone++;

            }

            counter2++;
            boxhelper++;

        }

    }

    public static boolean moreThanOnce(String mainWord, String testing) {
        // write your code here
        boolean more = false;

        int counter = 0;
        int times = 0;

        char character = testing.charAt(0);

        while (counter < mainWord.length()) {

            if (character == mainWord.charAt(counter)) {

                times++;

            }

            counter++;

        }

        if (times > 1) {
            more = true;
        }

        return more;
    }

    public static boolean moreThanTwice(String mainWord, String testing) {
        // write your code here
        boolean more = false;

        int counter = 0;
        int times = 0;

        char character = testing.charAt(0);

        while (counter < mainWord.length()) {

            if (character == mainWord.charAt(counter)) {

                times++;

            }

            counter++;

        }

        if (times > 2) {
            more = true;
        }

        return more;
    }

    public void keyDown(Key key) {
        if (reading) {
            if (key == Key.ENTER && input.text().length() == 5 && starting) {

                testedword = input.text();

            } else if (key == Key.LEFT && input.text().length() > 0 && starting) {

                input.text(input.text().substring(0, input.text().length() - 1));

            } else if (key.character().length() == 1 && starting) {

                input.text(input.text() + key.character());

            } else if (key.character().length() == 1 && guessing && letterx <= 500) {

                Text letter = new Text(screen, key.character(), 0, 0, Color.WHITE);
                letter.size(20);
                letter.bold(true);
                letter.center(letterx, lettery);
                list2.add(letter);

            } else if (key == Key.LEFT && list2.size() > 0 && guessing) {

                list2.get(list2.size() - 1).remove();
                list2.remove(list2.size() - 1);

                if (letterx > 300) {

                    letterx -= 50;
                    counter--;

                }

            } else if (key == Key.ENTER && guessing && list2.size() == 5) {

                check();
                lettery += 50;
                letterx = 300;
                counter = 1;
                counter2 = 0;
                helperword = "";
                list2.clear();

            }
        }
    }

    public void colorChange(int square, int num) {

        
        
        Color wordleGreen = new Color(83, 141, 78);
        Color wordleYellow = new Color(180, 159, 58);

        if (num == 1) {

            list.get(square).color(wordleGreen);
            checker++;
            System.out.println(checker);

        } else if (num == 2) {

            list.get(square).color(wordleYellow);

        }
        
        if (checker == 5) {
            
            playing = false;
            rainColor = Color.GREEN;
            changeAll();
            
        }

    }

    public void mouseDown(int button, Location location) {

        

            if (location.x() > 50 && location.x() < 100 && location.y() > 100 & location.y() < 125 && playing) {

                rainColor = (Color.BLUE);

            }

            if (location.x() > 50 && location.x() < 100 && location.y() > 140 & location.y() < 165 && playing) {

                rainColor = (Color.CYAN);

            }

            if (location.x() > 50 && location.x() < 100 && location.y() > 180 & location.y() < 205 && playing) {

                rainColor = (Color.RED);

            }

            if (location.x() > 50 && location.x() < 100 && location.y() > 220 & location.y() < 245 && playing) {

                rainColor = (Color.YELLOW);

            }

            if (location.x() > 50 && location.x() < 100 && location.y() > 260 & location.y() < 285 && playing) {

                rainColor = (betterpink);

            

        }

    }
    
    public void changeAll() {
        
        int changecounter = 0;
        
        while (changecounter < rain.size()) {

            Oval shape = rain.get(changecounter);
            
            shape.color(Color.GREEN);
            
            changecounter++;
            
        }
        
        
    }

    public void rainFall() {

        for (Oval shape : rain) {

            shape.move(0, 15);

        }

    }

    public void addRain() {

        int raincounter = 0;
        
        while (raincounter < 4) {
            Random random = new Random();

            Oval shape = new Oval(screen, random.nextInt(800), 0, 15, 15, rainColor);
        
            rain.add(shape);
            
            raincounter++;
        
        }

    }

    public void deleteRain() {

        int deletecounter = 0;

        while (deletecounter < rain.size()) {

            Oval shape = rain.get(deletecounter);

            if (shape.y() > 580 || shape.overlaps(wordle)) {
                rain.remove(shape);
                shape.remove();

            } else if (shape.overlaps(back) && playing) {
                
                shape.visible(false);
                
            } else if (shape.overlaps(shape3) && !guessing) {
                
                rain.remove(shape);
                shape.remove(); 
            } else if (!shape.overlaps(back) && playing) {
                
                shape.visible(true);
                
            }
            
                
            
           

            int deletecounter2 = 0;

            while (deletecounter2 < list3.size()) {

                if (shape.overlaps(list3.get(deletecounter2))) {

                    rain.remove(shape);
                    shape.remove();
                    

                }

                deletecounter2++;

            }

            deletecounter++;

        }

    }

}
