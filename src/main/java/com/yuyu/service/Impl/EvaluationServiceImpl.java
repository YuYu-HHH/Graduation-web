package com.yuyu.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.yuyu.common.ServerResponse;
import com.yuyu.dao.EvaluationMapper;
import com.yuyu.dao.OrderMapper;
import com.yuyu.pojo.Evaluation;
import com.yuyu.service.IEvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("iEvaluationService")
public class EvaluationServiceImpl implements IEvaluationService {

    @Autowired
    private EvaluationMapper evaluationMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ServerResponse<PageInfo> SelectAllByMenuId(int menu_id, int pageNum, int pageSize) {

        PageHelper.startPage(pageNum, pageSize);
        List<Evaluation> evaluations = evaluationMapper.selectbymenuid(menu_id);
        List<Evaluation> EvaluationList = Lists.newArrayList();
        for (Evaluation EvaluationItem : evaluations) {
            Evaluation evaluation = assembleEvaluationDtailVo(EvaluationItem);
            EvaluationList.add(evaluation);
        }
        PageInfo pageResult = new PageInfo(evaluations);
        pageResult.setList(EvaluationList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<Evaluation> SelectAllByEvaluationId(int evaluationId) {
        Evaluation evaluation = evaluationMapper.selectByPrimaryKey(evaluationId);
        if (evaluation!=null){
            return ServerResponse.createBySuccess(evaluationMapper.selectByPrimaryKey(evaluationId));
        }
        return ServerResponse.createByErrorMessage("查询失败");
    }

    @Override
    public ServerResponse<Evaluation> InsertEvaluation(Evaluation evaluation) {
        //添加评价
        int ResultCount = evaluationMapper.insert(evaluation);
        int ResultId = evaluationMapper.getIdBymenuid(evaluation.getMenuId());
        //在order_table 里面添加评价的id
        //int ResultMenuId = evaluation.getMenuId();
        orderMapper.updateEvaluationIdByitemid(evaluation.getMenuId(),ResultId);

        if (ResultCount > 0){
            return ServerResponse.createBySuccess(evaluation);
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }

    @Override
    public ServerResponse<PageInfo> SelectAllByStoreId(int store_id, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Evaluation> evaluations = evaluationMapper.selectbystoreid(store_id);
        List<Evaluation> EvaluationList = Lists.newArrayList();
        for (Evaluation EvaluationItem : evaluations) {
            Evaluation evaluation = assembleEvaluationDtailVo(EvaluationItem);
            EvaluationList.add(evaluation);
        }
        PageInfo pageResult = new PageInfo(evaluations);
        pageResult.setList(EvaluationList);
        return ServerResponse.createBySuccess(pageResult);
    }

    private Evaluation assembleEvaluationDtailVo(Evaluation evaluation) {
        Evaluation Evaluationnew = new Evaluation();

        Evaluationnew.setId(evaluation.getId());
        Evaluationnew.setFoodname(evaluation.getFoodname());
        Evaluationnew.setLevel(evaluation.getLevel());
        Evaluationnew.setAverage(evaluation.getAverage());
        Evaluationnew.setPictureone(evaluation.getPictureone());
        Evaluationnew.setPicturetwo(evaluation.getPicturetwo());
        Evaluationnew.setPicturethree(evaluation.getPicturethree());
        Evaluationnew.setText(evaluation.getText());

        return Evaluationnew;
    }
}
