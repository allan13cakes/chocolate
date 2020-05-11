package com.allan.launcher;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.allan.controller.MethodFinderController;
import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.body.MethodDeclaration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MethodFinderLauncher extends Application {
	private String path = "/Users/qiuguozhong/git_repository_test/myrepo";
	final ImageView rootIcon = new ImageView(
			new Image(getClass().getResourceAsStream("/img/folder.png"), 16, 16, false, false));

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		try {
			final FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MethodFinder.fxml"));
			final Parent root = (Parent) loader.load();
			final MethodFinderController controller = loader.<MethodFinderController>getController();

			Path paths = Paths.get(path);
			TreeItem<String> rootItem = new TreeItem<String>(paths.getFileName().toString(), rootIcon);
			rootItem.setExpanded(true);
			try {
				Files.list(paths).forEach(path -> {
					createTreeNode(rootItem, path);
				});
			} catch (IOException e) {
				e.printStackTrace();
			}

			controller.loadTreeItems(rootItem);

			final Scene scene = new Scene(root);
			stage.setScene(scene);
			stage.setTitle("Method Finder");

			stage.show();
		} catch (Exception e) {
			System.out.print(e);
		}

	}

	private void createTreeNode(TreeItem<String> parentNode, Path path) {
		if (Files.isDirectory(path)) {
			ImageView folderIcon = new ImageView(
					new Image(getClass().getResourceAsStream("/img/folder.png"), 16, 16, false, false));
			TreeItem<String> leaf = new TreeItem<String>(path.getFileName().toString(), folderIcon);
			parentNode.getChildren().add(leaf);
			parentNode.setExpanded(true);
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
				if (path.getFileName().toString().endsWith(".java")) {
					CompilationUnit unit = StaticJavaParser.parse(path);
					unit.findAll(MethodDeclaration.class).forEach(item -> {
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
