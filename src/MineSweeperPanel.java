import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeperPanel extends JFrame implements ActionListener {

    private JButton Casebutton;

    public MineSweeperPanel(MineSweeper mine){
        super("MineSweeper");
        this.setSize(mine.getNbCols()*50, mine.getNbRows()*50);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.setLayout(new GridLayout(mine.getNbRows(), mine.getNbCols()));
        for(int i = 0; i<mine.getNbRows();i++){
            for(int j = 0; j<mine.getNbCols();j++){
                this.add(new JButton());
            }
        }
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
