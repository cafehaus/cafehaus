import React from 'react'
import dayjs from 'dayjs'
import './Task.less'
import { Button, Radio, Table, Space, Popconfirm, Modal, Form, Input, DatePicker } from 'antd'

class Task extends React.Component {
  columns = [
    {
      title: '编号',
      dataIndex: 'id',
      key: 'id',
    },
    {
      title: '任务描述',
      dataIndex: 'des',
      key: 'des',
    },
    {
      title: '状态',
      dataIndex: 'status',
      key: 'status',
      render: (_, record) => (
        <span>{ record.status === '1' ? '已完成' : '未完成' }</span>
      ),
    },
    {
      title: '完成时间',
      dataIndex: 'completeTime',
      key: 'completeTime',
    },
    {
      title: '操作',
      key: 'action',
      width: 160,
      render: (_, record, index) => (
        <Space size="middle">
          <Popconfirm
            title="温馨提示"
            description="你确定要删除此任务吗?"
            onConfirm={() => this.handleDelete(record, index)}
          >
            <a>删除</a>
          </Popconfirm>
          { record.status === '2' ? <a onClick={() => this.handleComplete(record, index)}>完成</a> : null }
        </Space>
      ),
    },
  ]

  state = {
    status: '0',
    showModal: false,
    form: {
      des: '',
      completeTime: '',
    },
    tableData: [
      {
        key: '1',
        id: '1',
        des: '地势坤,君子以厚德载物',
        status: '1',
        completeTime: '2023-11-22 18:02:09',
      },
      {
        key: '2',
        id: '2',
        des: '天行健,君子以自强不息',
        status: '2',
        completeTime: '2023-11-25 12:16:12',
      },
    ]
  }
  handleComplete = (row, i) => {
    let { tableData } = this.state
    tableData[i].status = '1'
    tableData[i].completeTime = dayjs().format('YYYY-MM-DD HH:mm:ss')
    this.setState({
      tableData: [...tableData]
    })
  }
  handleDelete = (row, i) => {
    let { tableData } = this.state
    tableData.splice(i, 1)

    this.setState({
      tableData: [...tableData] // 注意这里不能直接用原数组
    })
  }
  handleAdd = () => {
    this.setState({
      showModal: true
    })
  }
  handleOk = () => {
    // 注意校验成功会直接走 then,校验不通过会走 catch
    this.formIns.validateFields().then(e => {
      let { tableData, form } = this.state
      const len = tableData.length
      const id = len ? (+tableData[len - 1].id + 1) : 1
      tableData.push({
        key: id,
        id: id,
        des: form.des,
        status: '2',
        completeTime: dayjs(form.completeTime).format('YYYY-MM-DD HH:mm:ss'),
      })

      this.setState({
        tableData: [...tableData] // 注意这里不能直接用原数组
      })
      this.handleCancel()
    }).catch(err => { console.log(err) })
  }
  handleCancel = () => {
    this.setState({
      showModal: false
    })
    this.formIns.resetFields()
  }

  onChange = ({ target: { value }}) => {
    this.setState({
      status: value
    })
  }

  onChangeDate = (e) => {
    this.setState({
      form: {
        ...this.state.form,
        completeTime: e
      }
    })
  }
  onChangeDes = ({ target: { value }}) => {
    this.setState({
      form: {
        ...this.state.form,
        des: value
      }
    })
  }

  render() {
    const { status, tableData, showModal, form } = this.state
    return (
      <>
        <div className='task'>
          <div className='task-header'>
            <h3>TASK OA 任务管理系统</h3>
            <Button type='primary' onClick={this.handleAdd}>新增任务</Button>
          </div>
          <div className='task-radio'>
            <Radio.Group buttonStyle="solid" onChange={this.onChange} value={status}>
              <Radio.Button value="0">全部</Radio.Button>
              <Radio.Button value="2">未完成</Radio.Button>
              <Radio.Button value="1">已完成</Radio.Button>
            </Radio.Group>
          </div>

          <Table columns={this.columns} dataSource={tableData} pagination={{ hideOnSinglePage: true }} />
        </div>
        <Modal title="新增任务" open={showModal} onOk={this.handleOk} onCancel={this.handleCancel}>
          <Form
            initialValues={form}
            labelCol={{ span: 6 }}
            ref={x => this.formIns = x}
          >
            <Form.Item label="任务描述" name="des" rules={[{ required: true }]}>
              <Input placeholder="请输入任务描述" vlaue={form.des} onChange={this.onChangeDes} />
            </Form.Item>
            <Form.Item label="预期完成时间" name="completeTime" rules={[{ required: true, type: 'date' }]}>
              <DatePicker format='YYYY-MM-DD HH:mm:ss'  showTime onChange={this.onChangeDate}  />
            </Form.Item>
          </Form>
        </Modal>
      </>
    )
  }
}

export default Task
