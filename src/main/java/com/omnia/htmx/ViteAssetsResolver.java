package com.omnia.htmx;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class ViteAssetsResolver {
    private static final String MANIFEST_JSON_PATH = "static/dist/.vite/manifest.json";

    private final ObjectMapper objectMapper;
    private ViteAssets viteAssets;

    public ViteAssetsResolver(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    public void init() {
        Resource resource = new ClassPathResource(MANIFEST_JSON_PATH);

        try (InputStream is = resource.getInputStream()) {
            JsonNode rootNode = objectMapper.readTree(is);
            String viteJsFile = rootNode.path("index.js").path("file").asText();
            List<String> viteCssFileList = rootNode.path("index.js").path("css")
                    .valueStream()
                    .map(jsonNode -> jsonNode.asText())
                    .toList();

            this.viteAssets = ViteAssets.of(viteJsFile, viteCssFileList);

            System.out.println("ViteAssets: " + this.viteAssets);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getViteJs() {
        return this.viteAssets.viteJs();
    }

    public List<String> getViteCss() {
        return this.viteAssets.viteCssList();
    }
}
