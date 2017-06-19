/**
 * 
 */
package com.gav.j17.airplaine.srv;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.gav.j17.airplaine.dto.SeatsDescr;
import com.gav.j17.airplaine.util.Loggable;

import ch.qos.logback.classic.Logger;

/**
 * @author alex
 *
 */
@Service
public class SeatsFileReader {
	@Loggable
	private Logger logger;
	
	public SeatsDescr readInput(String fileName) {
		logger.info("Parsing file {}", fileName);
		Resource resource = new ClassPathResource(fileName);
		
		Resource fileSysRes = new FileSystemResource(fileName);
		List<String> lines = new ArrayList<>();
		try (Stream<String> stream = Files.lines(Paths.get(resource.getURI()))) {
			lines = stream.collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		lines.forEach(logger::debug);
		return new SeatsDescr(0, 0);
	}
}
