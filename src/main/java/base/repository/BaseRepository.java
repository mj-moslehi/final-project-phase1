package base.repository;

import base.entity.BaseEntity;

import java.io.Serializable;

public interface BaseRepository <T extends BaseEntity<ID>, ID extends Serializable>{
}
