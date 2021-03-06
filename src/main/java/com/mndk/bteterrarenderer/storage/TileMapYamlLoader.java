package com.mndk.bteterrarenderer.storage;

import com.mndk.bteterrarenderer.BTETerraRenderer;
import com.mndk.bteterrarenderer.tms.TileMapService;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TileMapYamlLoader {
	
	private static final String DEFAULT_MAP_YAML_PATH = "assets/" + BTETerraRenderer.MODID + "/default_maps.yml";
	public static final Yaml YAML = new Yaml();

	private static File mapFilesDirectory;
	public static TileMapLoaderResult result;

	public static void refresh() throws Exception {

		result = new TileMapLoaderResult();
		
		result.append(loadDefaultMap());
		
		if(!mapFilesDirectory.exists()) {
			mapFilesDirectory.mkdirs();
		}
		else {
			File[] mapFiles = mapFilesDirectory.listFiles((dir, name) -> name.endsWith(".yml"));

			if (mapFiles != null) {
				for (File mapFile : mapFiles) {
					try {
						result.append(load(new FileReader(mapFile)));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	
	public static void refresh(String modConfigDirectory) throws Exception {
		mapFilesDirectory = new File(modConfigDirectory + "/" + BTETerraRenderer.MODID + "/maps");
		refresh();
	}
	

	@SuppressWarnings("unchecked")
	private static TileMapLoaderResult load(Reader fileReader) throws Exception {
		
		Map<String, Object> mapData = YAML.load(fileReader);
		Map<String, Object> categories = (Map<String, Object>) mapData.get("categories");
		
		List<TileMapLoaderResult.Category> result = new ArrayList<>();
		
		for(Map.Entry<String, Object> category : categories.entrySet()) {
			
			result.add(getMapCategoryFromMapObject(category.getKey(), (Map<String, Object>) category.getValue()));
			
		}
		return new TileMapLoaderResult(result);
	}
	
	
	private static TileMapLoaderResult loadDefaultMap() throws Exception {
		return load(new InputStreamReader(TileMapYamlLoader.class.getClassLoader().getResourceAsStream(DEFAULT_MAP_YAML_PATH)));
	}
	
	
	@SuppressWarnings("unchecked")
	private static TileMapLoaderResult.Category getMapCategoryFromMapObject(String name, Map<String, Object> mapList) throws Exception {
		List<TileMapService> mapSet = new ArrayList<>();
		
		for(Map.Entry<String, Object> map : mapList.entrySet()) {
			mapSet.add(TileMapService.parse(map.getKey(), (Map<String, Object>) map.getValue()));
		}
		
		return new TileMapLoaderResult.Category(name, mapSet);
	}
}
