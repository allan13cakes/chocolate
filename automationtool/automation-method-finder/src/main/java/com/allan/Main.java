package com.allan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

	private final String DEFAULT_TITLE = "Automation Method Finder";
	private String path = "/Users/qiuguozhong/git_repository_test/myrepo";

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle(DEFAULT_TITLE);

		/**
		 * tree part
		 */
		Path root = Paths.get(path);

		ImageView rootIcon = new ImageView(
				new Image(getClass().getResourceAsStream("/img/folder.png"), 16, 16, false, false));

		TreeItem<String> rootItem = new TreeItem<String>(root.getFileName().toString(), rootIcon);
		rootItem.setExpanded(true);
		Files.list(root).forEach(path -> {
			createTreeNode(rootItem, path);
		});

		VBox box = new VBox();

		GridPane gridpane = new GridPane();
		gridpane.setPadding(new Insets(5));
		gridpane.setHgap(5);
		gridpane.setVgap(5);
		

		TreeView<String> treeView = new TreeView<String>(rootItem);

		box.getChildren().add(treeView);

		Scene scene = new Scene(box, 400, 300);
		scene.setFill(Color.LIGHTGRAY);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void createTreeNode(TreeItem<String> parentNode, Path path) {
		if (Files.isDirectory(path)) {
			ImageView folderIcon = new ImageView(
					new Image(getClass().getResourceAsStream("/img/folder.png"), 16, 16, false, false));
			TreeItem<String> leaf = new TreeItem<String>(path.getFileName().toString(), folderIcon);
			parentNode.getChildren().add(leaf);

			try {
				Files.list(path).forEach(children -> {
					createTreeNode(leaf, children);
				});
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else if (Files.exists(path)) {
			ImageView fileIcon = new ImageView(
					new Image(getClass().getResourceAsStream("/img/file.png"), 16, 16, false, false));
			TreeItem<String> leaf = new TreeItem<String>(path.getFileName().toString(), fileIcon);

			try {
				System.out.println(path.getFileName());
				if (path.getFileName().toString().endsWith(".java")) {
					CompilationUnit unit = StaticJavaParser.parse(path);
					unit.findAll(MethodDeclaration.class).forEach(item -> {
						System.out.println(item.getName());
						ImageView codeIcon = new ImageView(
								new Image(getClass().getResourceAsStream("/img/code.png"), 16, 16, false, false));
						TreeItem<String> childLeaf = new TreeItem<String>(item.getNameAsString(), codeIcon);
						leaf.getChildren().add(childLeaf);
					});
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			parentNode.getChildren().add(leaf);
		}

	}

}
