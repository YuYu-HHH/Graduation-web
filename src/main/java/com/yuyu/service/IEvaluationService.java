package com.yuyu.service;

import com.github.pagehelper.PageInfo;
import com.yuyu.common.ServerResponse;
import com.yuyu.pojo.Evaluation;

public interface IEvaluationService {
    ServerResponse<PageInfo> SelectAllByMenuId(int menu_id, int pageNum, int pageSize);

    ServerResponse<Evaluation> SelectAllByEvaluationId(int evaluationId);

    ServerResponse<Evaluation> InsertEvaluation(Evaluation evaluation);

    ServerResponse<PageInfo> SelectAllByStoreId(int store_id, int pageNum, int pageSize);
}
