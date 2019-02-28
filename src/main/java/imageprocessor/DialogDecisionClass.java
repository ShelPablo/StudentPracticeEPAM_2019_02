package imageprocessor;



import javax.swing.*;

import java.util.List;



public class DialogDecisionClass extends JFrame {



    private void showDecision(List<Boolean> output) {

        int numb = -1;

        for (int i = 0; i < output.size(); i++) {

            if (output.get(i)) {

                numb = i;

            }

        }

        String finalMessage = "";

        switch (numb) {

            case 0:

                finalMessage = "It is 50 rub";

                break;

            case 1:

                finalMessage = "It is 100 rub";

                break;

            case 2:

                finalMessage = "It is 200 rub";

                break;

            case 3:

                finalMessage = "It is 500 rub";

                break;

            case 4:

                finalMessage = "It is 1000 rub";

                break;

            case 5:

                finalMessage = "It is 5000 rub";

                break;

            default:

                finalMessage = "It isn't banknote";



        }

        String finalMessage1 = finalMessage;



        final JOptionPane optionPane = new JOptionPane();

        optionPane.showMessageDialog(DialogDecisionClass.this,

                "<html><h2>" + finalMessage1 + "</h2>");



    }



    public DialogDecisionClass(List<Boolean> out) {

        showDecision(out);

    }



}
