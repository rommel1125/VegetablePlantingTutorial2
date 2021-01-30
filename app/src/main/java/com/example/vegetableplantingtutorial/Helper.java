package com.example.vegetableplantingtutorial;

public class Helper {

    public String getUri(int id) {
        String uri = "";

        switch(id) {

//              Talong (Eggplant)
            case 1:
                uri = "https://youtu.be/dHDKdczU2_U";
                break;

//                Tomato (Kamatis)
            case 2:
                uri = "https://youtu.be/eySTo2GgvoY";
                break;

//                Cabbage (Repolyo)
            case 3:
                uri = "https://youtu.be/TS_yDySB92s";
                break;

//                String beans (Sitaw)
            case 4:
                uri = "https://youtu.be/j6AUtQuMBME";
                break;

//                Squash (Kalabasa)
            case 5:
                uri = "https://youtu.be/dERQ_XwQtrY";
                break;

//                Moringa Leaves (Malunggay)
            case 6:
                uri = "https://youtu.be/liFdBSrM62Q";
                break;

//                Water Spinach (Kangkong)
            case 7:
                uri = "https://youtu.be/q9dFCCDUCCI";
                break;

//                Lettuce (Letsugas)
            case 8:
                uri = "https://youtu.be/VMH0ut0Ory4";
                break;

//                Bottle gourd (Upo)
            case 9:
                uri = "https://youtu.be/Q6jQ5ZvoDt0";
                break;

//                Bitter gourd (Ampalaya)
            case 10:
                uri = "https://youtu.be/5vvQyruajQk";
                break;

        }

        return uri;
    }

}
