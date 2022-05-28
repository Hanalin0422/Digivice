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
   
   public static Button refreshBtn = new Button("닫기");
   public static int sum = 0;
   public static int stage = 0;
   public static boolean isAlert3, isAlert4, isAlert5, isAlert6 = false;
   
   private int stack = 0;
   
   
   private Stage popStage = new Stage();
   private Stage gameStage = new Stage();
   
   
   public Image img1 = new Image(getClass().getResourceAsStream("/image/1.gif")); // 부화직전 알
   public Image img2 = new Image(getClass().getResourceAsStream("/image/2.gif")); // 깜몬 알안에
   public Image img3 = new Image(getClass().getResourceAsStream("/image/3.gif")); // 깜몬
   public Image img4 = new Image(getClass().getResourceAsStream("/image/4.gif")); // 코로몬
   public Image img5 = new Image(getClass().getResourceAsStream("/image/5.gif")); // 코로몬 먹는 모습
   public Image img6 = new Image(getClass().getResourceAsStream("/image/6.gif")); // 아구몬
   public Image img7 = new Image(getClass().getResourceAsStream("/image/7.gif")); // 아구몬 먹는 모습
   

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
            
            Button closeBtn = new Button("닫기");
            closeBtn.setOnAction(e-> windowClose(popStage));
            Text text = new Text("곧 알이 깨어날 것 같아요!");

            pane.setAlignment(text, Pos.CENTER);
            pane.setAlignment(closeBtn, Pos.BOTTOM_CENTER);
            pane.setMargin(closeBtn, new Insets(0, 0, 20, 0));
            pane.getChildren().addAll(text, closeBtn);
            Scene scene = new Scene(pane, 300, 200);
            
            popStage.setTitle("알림!");
            popStage.setResizable(false);
            popStage.setScene(scene);
            popStage.show();
         }
         else if(sum == 100 ) {
            egg.setImage(img2);
            ment.setText("밥을 주거나 함께 놀아주어 디지몬의 힘을 키워주세요!");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("디지몬이 부화했어요!");
            alert.setHeaderText("[깜몬]");
            alert.setContentText("이제 막 알에서 깨어난 유년기 디지몬이다.");
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
         alert.setHeaderText("너무 배불러 ㅠㅠ");
         alert.setTitle("과식은 좋지 않아!");
         alert.setContentText("POWER -5");
         alert.show();
         sum -=5;
      }else if(isAlert6 == false){
         switch(seed) {
         case 0:
            alert.setHeaderText("이거 너무 맜있어!");
            alert.setTitle("아직은 배고플 나이");
            alert.setContentText("POWER +10");
            alert.show();
            stack += 1;
            sum +=10;
            break;
         case 1:
            alert.setHeaderText("많이 먹고 열심히 싸울게!");
            alert.setTitle("힘을 키우려면 많이 먹어야해");
            alert.setContentText("POWER +10");
            alert.show();
            stack += 1;
            sum +=10;
            break;
         case 2:
            alert.setHeaderText("더 줘! 더 줘!");
            alert.setTitle("먹어도 배고프다");
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
      gameStage.setTitle("숫자 야구 게임");
      gameStage.setResizable(false);
      if(isAlert6 == false) {
    	  gameStage.show();
      }
   }
   
   public void baseballGame() {
      try {
         int number = Integer.parseInt(answer.getText());
         
           System.out.println(Random_Int); //랜덤 변수 뭐인지
           int H=Random_Int/100;//랜덤변수 자리수대로 나누기
           int T=Random_Int%100/10;
           int O=Random_Int%100%10;
           int H1,T1,O1;//입력받는 변수 분리 비교용
           int S,B;//스트라이크, 볼
           
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
               alertN.setTitle("오류!");
               alertN.setHeaderText("숫자가 너무 작거나 큽니다!!!");
               alertN.setContentText("3자리 정수만 입력 가능 합니다"); 
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
               result.setText("홈런입니다!");
               exit.setText("POWER +100 획득!");
               answer.setEditable(false);
               refreshBtn.setVisible(true);
            }
            else if(S==0&&B==0){
               result.setText(String.valueOf(number)+" 아웃입니다 ㅜㅜ");
               } 
            else{
               result.setText(String.valueOf(S)+" 스트라이크 "+String.valueOf(B)+" 볼 ");
               }
            } 

      }catch(Exception e){
         Alert alert = new Alert(AlertType.ERROR);
         alert.setTitle("오류!");
         alert.setHeaderText("숫자를 입력해주세요");
         alert.setContentText("띄어쓰기도 안됩니다.");
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
         alert.setTitle(">> 깜몬이 알을 벗었다! <<");
         alert.setHeaderText("안녕?");
         alert.setContentText("난 너의 파트너 디지몬이야. 잘부탁해.");
         if(isAlert3 == false){
        	 alert.show();
        	 isAlert3 = true;
        	 }
         
         egg.setImage(img3);
      }else if(stage == 4) {
         Alert alert = new Alert(AlertType.INFORMATION);
         alert.setTitle(">> 깜몬 진화 <<");
         alert.setHeaderText("[코로몬]");
         alert.setContentText("깜몬이 진화한 형태로 유년기 디지몬이다.\n필살기는 큼지막한 입에서 거품을 뿜어내 적을 놀라게 하는 거품 뿜기이다.");
         if(isAlert4 == false){
        	 alert.show();
        	 isAlert4 = true;
        	 }
         egg.setImage(img4);
      }else if(stage == 5) {
    	  Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle(">> 코로몬 진화 <<");
          alert.setHeaderText("[아구몬]");
          alert.setContentText("코로몬이 진화한 성장기 디지몬.\n필살기는 입으로 화염의 숨결을 내뱉는 꼬마 불꽃이다.");
          if(isAlert5 == false){
         	 alert.show();
         	 isAlert5 = true;
         	 }
         egg.setImage(img6);
      }else if(stage == 6) {
    	  Alert alert = new Alert(AlertType.INFORMATION);
          alert.setTitle(">>>> POWER MAX <<<<");
          alert.setHeaderText("힘이 넘쳐!!!");
          alert.setContentText("고마워!! 덕분에 힘이 넘쳐나!\n 조금만 기다려, 곧 너의 세계로 갈게!");
          alert.show();
          isAlert6 = true;
          egg.setImage(img6);
      }
   }
   
}
