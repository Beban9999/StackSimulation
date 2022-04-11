package com.example.nar;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {


    public ListView<String> leftListView;
    public  ObservableList<String> lefts;
    public TextField tfInp;
    public Label infos;

    public void start(Stage stage) throws IOException {


        VBox vb1 = new VBox(20);

        lefts = FXCollections.observableArrayList();
        leftListView = new ListView<String>(lefts);
        leftListView.setPrefWidth(450);
        leftListView.setPrefHeight(320);
        leftListView.setStyle("-fx-font-size: 20");

        vb1.getChildren().add(leftListView);

        HBox hbTekst = new HBox(20);
        tfInp = new TextField();
        Button push = new Button("Push");
        Button pop = new Button("Pop");

        push.setOnAction(e->{
            String s = tfInp.getText();
            pushStack(s);

        });
        pop.setOnAction(e->{
            if(lefts.size() > 0){
                lefts.remove(lefts.get(lefts.size()-1));
            }

        });



        HBox hbActions = new HBox(20);

        Button add = new Button("ADD");
        add.setOnAction(e->{
            operationStack("+");
        });
        Button sub = new Button("SUB");
        sub.setOnAction(e->{
            operationStack("-");
        });
        Button mul = new Button("MUL");
        mul.setOnAction(e->{
            operationStack("*");
        });
        Button dev = new Button("DEV");
        dev.setOnAction(event -> {
            operationStack("/");
        });


        infos = new Label();
        hbActions.getChildren().addAll(add, sub, mul, dev, infos);

        hbTekst.getChildren().addAll(tfInp, push,pop);
        HBox hMain = new HBox(20);
        VBox right = new VBox(30);
        right.getChildren().addAll(hbTekst, hbActions);
        hMain.getChildren().addAll(vb1, right);
        Scene scene = new Scene(hMain, 800, 400);
        stage.setTitle("STACK");
        stage.setScene(scene);
        stage.show();
    }
    public  void pushStack(String s){
        infos.setText("");
        try{
            int broj = Integer.parseInt(s);
            if(broj >= 0 && broj <= 127){
                lefts.add("\t"+broj+"\t\t\t" + String.format("%8s", Integer.toBinaryString(broj)).replace(' ', '0'));
                tfInp.setText("");
            }
            else if(broj < 0 && broj >= -127){
                lefts.add("\t"+broj+"\t\t\t" + Integer.toBinaryString(broj).substring(Integer.toBinaryString(broj).length()-8));
                tfInp.setText("");
            }
            else{
                infos.setText("OPSEG (-127 <= x >= 127)");
            }

        }
        catch (Exception exception){
            infos.setText("Uneti validan broj!");
        }
    }
    public void operationStack(String op){
        infos.setText("");
        if(op.equals("+")){
            if(lefts.size() >=2){

                int op1 = Integer.parseInt(lefts.get(lefts.size()-1).substring(0,4).replaceAll("\t", ""));
                int op2 = Integer.parseInt(lefts.get(lefts.size()-2).substring(0,4).replaceAll("\t", ""));

                String s = String.valueOf(op1+op2);
                System.out.println(s);
                //lefts.remove(lefts.size()-1); lefts.remove(lefts.size()-1);
                pushStack(s);

            }
        }
        if(op.equals("-")){
            if(lefts.size() >=2){

                int op1 = Integer.parseInt(lefts.get(lefts.size()-1).substring(0,4).replaceAll("\t", ""));
                int op2 = Integer.parseInt(lefts.get(lefts.size()-2).substring(0,4).replaceAll("\t", ""));

                String s = String.valueOf(op1-op2);
                System.out.println(s);
                lefts.remove(lefts.size()-1); lefts.remove(lefts.size()-1);
                pushStack(s);

            }
        }
        if(op.equals("*")){
            if(lefts.size() >=2){

                int op1 = Integer.parseInt(lefts.get(lefts.size()-1).substring(0,4).replaceAll("\t", ""));
                int op2 = Integer.parseInt(lefts.get(lefts.size()-2).substring(0,4).replaceAll("\t", ""));

                String s = String.valueOf(op1*op2);
                System.out.println(s);
                lefts.remove(lefts.size()-1); lefts.remove(lefts.size()-1);
                pushStack(s);

            }
        }
        if(op.equals("/")){
            if(lefts.size() >=2){

                int op1 = Integer.parseInt(lefts.get(lefts.size()-1).substring(0,4).replaceAll("\t", ""));
                int op2 = Integer.parseInt(lefts.get(lefts.size()-2).substring(0,4).replaceAll("\t", ""));

                try{
                    String s = String.valueOf(op1/op2);
                    System.out.println(s);
                    lefts.remove(lefts.size()-1); lefts.remove(lefts.size()-1);
                    pushStack(s);
                }
                catch (Exception e){
                    infos.setText("DELJENJE SA NULOM!");
                }


            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}