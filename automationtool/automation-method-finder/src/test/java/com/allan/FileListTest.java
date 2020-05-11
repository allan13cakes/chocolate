package com.allan;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.junit.Test;

public class FileListTest {

	@Test
	public void test() throws IOException {
		String path = "/Users/qiuguozhong/git_repository_test/myrepo";
		Path root = Paths.get(path);
		System.out.println(root.getFileName());
		Stream<Path> list = Files.list(root);
		list.forEach(System.out::println);
	}

}
