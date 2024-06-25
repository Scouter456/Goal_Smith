package com.scouter.goalsmith.datagen;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableOfContentsGenerator {

    private final List<WikiPageBuilder> wikiPages;

    public TableOfContentsGenerator() {
        this.wikiPages = new ArrayList<>();
    }

    public TableOfContentsGenerator(List<WikiPageBuilder> wikiPages) {
        this.wikiPages = wikiPages;
    }

    public void addPage(WikiPageBuilder builder) {
        this.wikiPages.add(builder);
    }

    public void generateTOC(String outputPath) {
        StringBuilder toc = new StringBuilder("# Table of Contents\n\n");

        for (WikiPageBuilder page : wikiPages) {
            toc.append("## ").append(page.getTitle()).append("\n\n");

            for (String heading : page.getHeadings()) {
                int level = heading.indexOf(' ');
                String title = heading.substring(level + 1);
                toc.append("   ".repeat(level - 1)).append("- [").append(title).append("](#").append(title.toLowerCase().replace(" ", "-")).append(")\n");
            }
            toc.append("\n");
        }

        try {
            File tocFile = new File(outputPath, "TableOfContents.md");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(tocFile))) {
                writer.write(toc.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
