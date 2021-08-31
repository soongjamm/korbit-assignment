package com.corbit.candlechart.utility.importer;

import java.util.List;

public interface Importable {
	List<String[]> load(String path);
}
