package com.wt.common.schedule.model;

import com.wt.common.core.annotations.Id;
import com.wt.common.core.annotations.Table;
import com.wt.common.core.entity.BaseEntity;
import com.wt.common.schedule.enums.ScheduleJobStatus;
import org.hibernate.validator.constraints.NotEmpty;

import java.text.MessageFormat;

@Table(name="ScheduleJobT")
public class ScheduleJob extends BaseEntity<ScheduleJob>{

    @Id(name = "jobId")
    private String jobId;

    @NotEmpty(message = "作业名称不能为空")
    private String jobName;

    @NotEmpty(message = "作业组不能为空")
    private String jobGroup;

    @NotEmpty(message = "作业类不能为空")
    private String jobClass;

    private String expression;

    private ScheduleJobStatus jobStatus;

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId == null ? null : jobId.trim();
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName == null ? null : jobName.trim();
    }

    public String getJobGroup() {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup) {
        this.jobGroup = jobGroup == null ? null : jobGroup.trim();
    }

    public String getJobClass() {
        return jobClass;
    }

    public void setJobClass(String jobClass) {
        this.jobClass = jobClass == null ? null : jobClass.trim();
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression == null ? null : expression.trim();
    }

    public ScheduleJobStatus getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(ScheduleJobStatus jobStatus) {
        this.jobStatus = jobStatus;
    }

    @Override
    public String toString() {
        return MessageFormat.format("调度作业名称：{0}，调度作业组：{1}，类：{2}，触发条件：{3}",this.jobName,this.jobGroup,this.jobClass,this
                .expression);
    }
}