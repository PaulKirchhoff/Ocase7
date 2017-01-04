package ocase7;

import java.io.File;
import ocase7.view3.View3;
import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import ocase7.launchView.LaunchView;
import ocase7.loginView.LoginView;
import ocase7.view2.View2;

/**
 *
 * @author PaulsBook
 */
public class mainView extends Application {
    
     @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ocase 7");
        primaryStage.setMinHeight(800);
//        primaryStage.setMaxHeight(800);
        primaryStage.setMinWidth(660);
//        primaryStage.setMaxWidth(600);
        // erstelle View3
//        View3 view3 = new View3(primaryStage);
//        Scene view3Scene =  view3.createView3();
        // erstelle View2
//        View2 view2 = new View2(primaryStage);
//        Scene view2Scene = view2.createView2Scene();
        LaunchView launchView = new LaunchView();
        
        
        
        Scene launchViewScene = launchView.createLaunchView();
        LoginView lv = new LoginView(primaryStage);
        Scene loginView = lv.creatLoginView();
        //primaryStage.setScene(launchViewScene);
        
        
//        // Bindet Musik an den LaunchView, wenns stört einfach auskommentieren ;)
//        String musicFile = "src/ocase7/launchView/Ocase7Intro.aif";  
//        //String musicFile = "/Users/PaulsBook/NetBeansProjects/Ocase7/src/ocase7/launchView/Ocase7Intro.aif";  
//        Media introSound = new Media(new File(musicFile).toURI().toString());
//        MediaPlayer mediaPlayer = new MediaPlayer(introSound);
//        mediaPlayer.setAutoPlay(true);
//        MediaView mv = new MediaView(mediaPlayer);
//        ((Group)launchViewScene.getRoot()).getChildren().addAll(mv);
        
        primaryStage.setScene(loginView);
        
        
        //primaryStage.setScene(loginView);
        //LoginView loginView = new LoginView();
        //Scene loginscene = loginView.createLoginView();
        Task<Void> sleeper = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                try {
//                    ExecutorService es = Executors.newFixedThreadPool(7);
//                    es.shutdown();
//                    es.awaitTermination(10, TimeUnit.SECONDS);
//                    es.shutdownNow();
//                    Thread thread = new Thread();
//                    thread.setDaemon(true);
                    Thread.sleep(10000);
                    //System.out.println(Thread.activeCount());
                } catch (InterruptedException e) {
                    throw new ExceptionInInitializerError(e.getMessage());
                }
                return null;
            }
        };
        
        //System.out.println(Thread.activeCount());
        
        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent event) {
                //primaryStage.setScene(loginView);
            }
        });
         new Thread(sleeper).start();
        
        //ocaseLoginView.LoginView loginView = new LoginView();
        //Scene loginScene = loginView.createLoginView();
        
        //primaryStage.setScene(lv.createLaunchView());
        //primaryStage.setScene(view3Scene);
        //primaryStage.setScene(launchViewScene);
        //primaryStage.setFullScreen(true);
        primaryStage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
