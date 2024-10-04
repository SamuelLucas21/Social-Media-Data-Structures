package Body;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.SimpleEmail;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.util.Random;

import java.io.StringWriter;
import java.io.PrintWriter;

public class SendEmail{
			private final String meuEmail = "samukaluka2003@gmail.com";
			private final String minhaSenha = "obsv nmof hxom mcwq";
			private SimpleEmail email = new SimpleEmail();
			private int o;
				public SendEmail(String newEmail) {
					email.setHostName("smtp.gmail.com");
					email.setSmtpPort(465);
					email.setAuthenticator(new DefaultAuthenticator(meuEmail, minhaSenha));
					email.setSSLOnConnect(true);
					Random gerar = new Random();
					o = gerar.nextInt();
						while(true) {
							if(o>0)break;
							o=gerar.nextInt();
						}
							try {
								email.setFrom(meuEmail);
								email.setSubject("Deseja obter mais informações?");
								email.setMsg("Seu código é: "+String.valueOf(o));
								email.addTo(newEmail);
								email.send();
								
								Alert alert = new Alert(AlertType.INFORMATION);
								alert.setTitle("Aviso!");
								alert.setHeaderText("E-mail enviado com Sucesso!");
								alert.setContentText("Porfavor, após o uso da senha temporária crie uma nova em configurações.");
								alert.showAndWait();
								
							}catch(Exception ei) {
								Alert alert = new Alert(AlertType.ERROR);
								alert.setTitle("Aviso!");
								alert.setHeaderText("Erro ao enviar o E-mail");
								alert.setContentText("Porfavor, após o uso da senha temporária crie uma nova em configurações.");
								StringWriter sw = new StringWriter();
								PrintWriter pw = new PrintWriter(sw);
								ei.printStackTrace(pw);
								String exceptionText = sw.toString();
								
								TextArea textArea = new TextArea(exceptionText);
								textArea.setEditable(false);
								textArea.setWrapText(true);

								textArea.setMaxWidth(Double.MAX_VALUE);
								textArea.setMaxHeight(Double.MAX_VALUE);
								GridPane.setVgrow(textArea, Priority.ALWAYS);
								GridPane.setHgrow(textArea, Priority.ALWAYS);

								Label label = new Label("A Exception Stacktrace foi:");
								GridPane expContent = new GridPane();
								expContent.setMaxWidth(Double.MAX_VALUE);
								expContent.add(label, 0, 0);
								expContent.add(textArea, 0, 1);

								alert.getDialogPane().setExpandableContent(expContent);
								alert.showAndWait();
								ei.printStackTrace();
							}
				}
			public int getCode() { return this.o;}
}
