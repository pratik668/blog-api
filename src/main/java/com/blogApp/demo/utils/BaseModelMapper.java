package com.blogApp.demo.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public interface BaseModelMapper {

	static ModelMapper mapper = getMapper();

	static ModelMapper getMapper() {
		if (mapper == null) {
			return new ModelMapper();
		}
		return mapper;
	}

	default <T, E> E convert(T source) {
		mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return (E) mapper.map(source, this.getClass());

	}
}
