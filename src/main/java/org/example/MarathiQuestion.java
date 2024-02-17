package org.example;

public class MarathiQuestion {
   static public String translateToMarathi(String englishText) {
       return switch (englishText) {
           case "This bar graph is for?" -> "This bar graph is for?<br>#हा स्तंभालेख . . . . . साठी आहे. <br>";
           case "Scale used in this graph is?" -> "Scale used in this graph is?<br>#या स्तंभालेखासाठी वापरलेले प्रमाण . . . .  आहे.<br>";
           case "How much is the total of travellers for top $3$ values?" ->
                   "How much is the total of travellers for top $3$ values?<br>#सगळ्यात जास्त वापरल्या जाणाऱ्या पहिल्या तीन वाहनांसाठी प्रवाशांची एकूण संख्या किती?<br>";
           case "Which vehicle is used least for travelling?" ->
                   "Which vehicle is used least for travelling?<br>#प्रवासासाठी सर्वात कमी वापर कोणत्या वाहनाचा होतो?<br>";
           case "Which vehicle is used most for travelling?" ->
                   "Which vehicle is used most for travelling?<br>#प्रवासासाठी सर्वात जास्त वापर कोणत्या वाहनाचा होतो?<br>";
           case "How many different vehicles do travellers use?" ->
                   "How many different vehicles do travellers use?<br>#प्रवासासाठी एकूण किती वेगवेगळी वाहने वापरली गेली आहेत?<br>";
           case "Which are the different vehicles used by travellers?" ->
                   "Which are the different vehicles used by travellers?<br>#प्रवासासाठी वापरलेली वेगवेगळी वाहने कोणती आहेत?<br>";
           case "Which vehicle is used most?" -> "Which vehicle is used most?<br>#कोणता वाहन सर्वाधिक वापरला जातो?<br>";
           case "Which vehicle is used least?" ->
                   "Which vehicle is used least?<br>#कोणता वाहन सर्वाधिक कमी वापरला जातो?<br>";
           case "How much is the difference in the number of travellers between the vehicle used most and least?" ->
                   "How much is the difference in the number of travellers between the vehicle used most and least?<br>#प्रवासासाठी सगळ्यात जास्त वापरले जाणारे आणि सगळ्यात कमी वापरले जाणारे वाहन, यांच्या प्रवाशातील फरकाची संख्या किती आहे?<br>";
           case "How many are the total travellers travelling by the vehicles used most and least?" ->
                   "How many are the total travellers travelling by the vehicles used most and least?<br>#प्रवासासाठी सगळ्यात जास्त वापरले जाणारे वाहन आणि सगळ्यात कमी वापरले जाणारे वाहन, यांच्यातील प्रवाशांची एकूण संख्या किती आहे?<br>";
           case "How many are the total travellers?" ->
                   "How many are the total travellers?<br>#एकूण प्रवासी किती आहेत?<br>";
           case "How many are the total of travellers travelling by the second and third most used vehicle?" ->
                   "How many are the total of travellers travelling by the second and third most used vehicle?<br>#दोन क्रमांकाचे सर्वात जास्त वापरले जाणारे  वाहन आणि तीन क्रमांकाचे सर्वात जास्त वापरले जाणारे वाहन यांची मिळून एकूण प्रवाशांची एकूण संख्या किती?<br>";
           default -> englishText;
       };
    }

}
