package com.example.vegetableplantingtutorial;

public class Helper {

    public String getUri(int id) {
        String uri = "";

        switch(id) {

//              Talong (Eggplant)
            case 1:
                uri = "https://www.youtube.com/watch?v=13xAS6ebfBk";
                break;

//                Tomato (Kamatis)
            case 2:
                uri = "https://www.youtube.com/watch?v=dHS9O49JGj8";
                break;

//                Bok choy (Pechay)
            case 3:
                uri = "https://www.youtube.com/watch?v=OcLfCIT0q4A";
                break;

//                String beans (Sitaw)
            case 4:
                uri = "https://www.youtube.com/watch?v=hf9M2tpioYg";
                break;

//                Squash (Kalabasa)
            case 5:
                uri = "https://www.youtube.com/watch?v=rg4GItxtj_E";
                break;

//                Horseradish (Malunggay)
            case 6:
                uri = "https://www.youtube.com/watch?v=liFdBSrM62Q";
                break;

//                Spinach (Kangkong)
            case 7:
                uri = "https://www.youtube.com/watch?v=q9dFCCDUCCI";
                break;

//                Mung beans (Munggo)
            case 8:
                uri = "https://www.youtube.com/watch?v=z_XzC4mO-IE";
                break;

//                Bottle gourd (Upo)
            case 9:
                uri = "https://www.youtube.com/watch?v=Q6jQ5ZvoDt0";
                break;

//                Bitter gourd (Ampalaya)
            case 10:
                uri = "https://www.youtube.com/watch?v=5vvQyruajQk";
                break;

        }

        return uri;
    }

}
