package application;

import java.util.Random;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Controller{
   public ImageView egg;
   public Label score, ment, ex, result, exit;
   public TextField answer;
   public Button prey, play;
   
   public static Button refreshBtn = new Button("�ݱ�");
   public static int sum = 0;
   public static int stage = 0;
   public static boolean isAlert3, isAlert4, isAlert5, isAlert6 = false;
   
   private int stack = 0;
   
   
   private Stage popStage = new Stage();
   private Stage gameStage = new Stage();
   
   
   public Image img1 = new Image(getClass().getResourceAsStream("/image/1.gif")); // ��ȭ���� ��
   public Image img2 = new Image(getClass().getResourceAsStream("/image/2.gif")); // ���� �˾ȿ�
   public Image img3 = new Image(getClass().getResourceAsStream("/image/3.gif")); // ����
   public Image img4 = new Image(getClass().getResourceAsStream("/image/4.gif")); // �ڷθ�
   public Image img5 = new Image(getClass().getResourceAsStream("/image/5.gif")); // �ڷθ� �Դ� ���
   public Image img6 = new Image(getClass().getResourceAsStream("/image/6.gif")); // �Ʊ���
   public Image img7 = new Image(getClass().getResourceAsStream("/image/7.gif")); // �Ʊ��� �Դ� ���
   

   Random_Maker Rand = new Random_Maker();
   String Random_S = Rand.random_mak();
   int Random_Int=Integer.parseInt(Random_S);

   public void touch() {
      if(egg.getOnMouseClicked() != null) {
         stack = 0;
         if(stage < 2) {
            sum += 4;
         }
         
         if(sum == 60 ) {
            egg.setImage(img1);
            stage = 1;
            
            StackPane pane = new StackPane();
            popStage.setOnCloseRequest(e->{
               e.consume();
               windowClose(popStage);
            });
            
            Button closeBtn = new Button("�ݱ�");
            closeBtn.setOnAction(e-> windowClose(popStage));
            Text text = new Text("�� ���� ��� �� ���ƿ�!");

            pane.setAlignment(text, Pos.CENTER);
            pane.setAlignment(closeBtn, Pos.BOTTOM_CENTER);
            pane.setMargin(closeBtn, new Insets(0, 0, 20, 0));
            pane.getChildren().addAll(text, closeBtn);
            Scene scene = new Scene(pane, 300, 200);
            
            popStage.setTitle("�˸�!");
            popStage.setResizable(false);
            popStage.setScene(scene);
            popStage.show();
         }
         else if(sum == 100 ) {
            egg.setImage(img2);
            ment.setText("���� �ְų� �Բ� ����־� �������� ���� Ű���ּ���!");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("�������� ��ȭ�߾��!");
            alert.setHeaderText("[����]");
            alert.setContentText("���� �� �˿��� ��� ����� �������̴�.");
            alert.show();
            sum = 101;
            stage = 2;
         }
         if (stage == 2) {
            sum++;
            prey.setVisible(true);
            play.setVisible(true);
            ex.setVisible(true);
         }else if(stage == 3 || stage == 4 || stage == 5){
            sum++;
         }

         StageController();
         if(stage == 4) {
       	  egg.setImage(img4);
         }else if(stage == 5) {
       	  egg.setImage(img6);
         }
         score.setText(Integer.toString(sum));
      }
   }
   
   private void windowClose(Stage closeStage){
      closeStage.close();
   }
   
   public void givePrey() {
      Alert alert = new Alert(AlertType.INFORMATION);
      Random random = new Random();
      int seed = random.nextInt(3);
      
      if(stack == 5) {
         alert.setHeaderText("�ʹ� ��ҷ� �Ф�");
         alert.setTitle("������ ���� �ʾ�!");
         alert.setContentText("POWER -5");
         alert.show();
         sum -=5;
      }else if(isAlert6 == false){
         switch(seed) {
         case 0:
            alert.setHeaderText("�̰� �ʹ� ���־�!");
            alert.setTitle("������ ����� ����");
            alert.setContentText("POWER +10");
            alert.show();
            stack += 1;
            sum +=10;
            break;
         case 1:
            alert.setHeaderText("���� �԰� ������ �ο��!");
            alert.setTitle("���� Ű����� ���� �Ծ����");
            alert.setContentText("POWER +10");
            alert.show();
            stack += 1;
            sum +=10;
            break;
         case 2:
            alert.setHeaderText("�� ��! �� ��!");
            alert.setTitle("�Ծ �������");
            alert.setContentText("POWER +10");
            alert.show();
            stack += 1;
            sum +=10;
            break;
         }
      }
      
      StageController();
      if(stage == 4) {
    	  egg.setImage(img5);
      }else if(stage == 5) {
    	  egg.setImage(img7);
      }
      score.setText(Integer.toString(sum));
   }
   
   public void playWith() throws Exception {
      stack = 0;

      AnchorPane baseballPane=FXMLLoader.load(getClass().getResource("BaseBallGame.fxml"));
      gameStage.setOnCloseRequest(e->{
         e.consume();
         windowClose(gameStage);
      });
      
      refreshBtn.setVisible(false);
      refreshBtn.setOnAction(e-> {
         windowClose(gameStage);
         sum+=100;
         StageController();
         if(stage == 4) {
       	  egg.setImage(img4);
         }else if(stage == 5) {
       	  egg.setImage(img6);
         }
         score.setText(Integer.toString(sum));
         }
      );
      
      baseballPane.setBottomAnchor(refreshBtn, 20.0);
      baseballPane.setRightAnchor(refreshBtn, 130.0);
      baseballPane.getChildren().add(refreshBtn);
      Scene gameScene  = new Scene(baseballPane);
      
      gameStage.setScene(gameScene);
      gameStage.setTitle("���� �߱� ����");
      gameStage.setResizable(false);
      if(isAlert6 == false) {
    	  gameStage.show();
      }
   }
   
   public void baseballGame() {
      try {
         int number = Integer.parseInt(answer.getText());
         
           System.out.println(Random_Int); //���� ���� ������
           int H=Random_Int/100;//�������� �ڸ������ ������
           int T=Random_Int%100/10;
           int O=Random_Int%100%10;
           int H1,T1,O1;//�Է¹޴� ���� �и� �񱳿�
           int S,B;//��Ʈ����ũ, ��
           
           while(true) {
               if(Random_Int>100) {break;}
               else {
                   Random_S = Rand.random_mak();
                   Random_Int=Integer.parseInt(Random_S);
                   }
               }
          

            S=0; B=0;

            if(number>1000 || number<100) {
               Alert alertN = new Alert(AlertType.WARNING);
               alertN.setTitle("����!");
               alertN.setHeaderText("���ڰ� �ʹ� �۰ų� Ů�ϴ�!!!");
               alertN.setContentText("3�ڸ� ������ �Է� ���� �մϴ�"); 
               alertN.show();
            }
            else{
                H1=number/100;
                T1=number%100/10;
                O1=number%100%10;
                if(H==H1) {S++;}
                if(H==T1) {B++;}
                if(H==O1) {B++;}
                if(T==H1) {B++;}
                if(T==T1) {S++;}
                if(T==O1) {B++;}
                if(O==H1) {B++;}
                if(O==T1) {B++;}
                if(O==O1) {S++;}
                
            
            if(S==3) {
               result.setText("Ȩ���Դϴ�!");
               exit.setText("POWER +100 ȹ��!");
               answer.setEditable(false);
               refreshBtn.setVisible(true);
            }
            else if(S==0&&B==0){
               result.setText(String.valueOf(number)+" �ƿ��Դϴ� �̤�");
               } 
            else{
               result.setText(String.valueOf(S)+" ��Ʈ����ũ "+String.valueOf(B)+" �� ");
               }
            } 

      }catch(Exception e){
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("����!");
         alert.setHeaderText("���ڸ� �Է����ּ���");
         alert.setContentText("���⵵ �ȵ˴ϴ�.");
         alert.show();
      }
   }

   public void StageController() {
      if(300< sum && sum < 500) {
         stage = 3;
      }else if(500<= sum && sum < 800) {
         stage = 4;
      }else if(800<= sum && sum < 1000) {
         stage = 5;
      }else if (1000<= sum) {
    	  sum = 1000;
    	  stage = 6;
      }

      if(stage == 3) {
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle(">> ������ ���� ������! <<");
         alert.setHeaderText("�ȳ�?");
         alert.setContentText("�� ���� ��Ʈ�� �������̾�. �ߺ�Ź��.");
         if(isAlert3 == false){
        	 alert.show();
        	 isAlert3 = true;
        	 }
         
         egg.setImage(img3);
      }else if(stage == 4) {
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle(">> ���� ��ȭ <<");
         alert.setHeaderText("[�ڷθ�]");
         alert.setContentText("������ ��ȭ�� ���·� ����� �������̴�.\n�ʻ��� ŭ������ �Կ��� ��ǰ�� �վ ���� ���� �ϴ� ��ǰ �ձ��̴�.");
         if(isAlert4 == false){
        	 alert.show();
        	 isAlert4 = true;
        	 }
         egg.setImage(img4);
      }else if(stage == 5) {
    	  Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle(">> �ڷθ� ��ȭ <<");
          alert.setHeaderText("[�Ʊ���]");
          alert.setContentText("�ڷθ��� ��ȭ�� ����� ������.\n�ʻ��� ������ ȭ���� ������ ����� ���� �Ҳ��̴�.");
          if(isAlert5 == false){
         	 alert.show();
         	 isAlert5 = true;
         	 }
         egg.setImage(img6);
      }else if(stage == 6) {
    	  Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle(">>>> POWER MAX <<<<");
          alert.setHeaderText("���� ����!!!");
          alert.setContentText("����!! ���п� ���� ���ĳ�!\n ���ݸ� ��ٷ�, �� ���� ����� ����!");
          alert.show();
          isAlert6 = true;
          egg.setImage(img6);
      }
   }
   
}
