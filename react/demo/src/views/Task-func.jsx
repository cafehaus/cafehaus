import React, { useState, useRef } from 'react'
import dayjs from 'dayjs'
import './Task.less'
import { Button, Radio, Table, Space, Popconfirm, Modal, Form, Input, DatePicker } from 'antd'

function Task() {
  const columns = [
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
            onConfirm={() => handleDelete(record, index)}
          >
            <a>删除</a>
          </Popconfirm>
          { record.status === '2' ? <a onClick={() => handleComplete(record, index)}>完成</a> : null }
        </Space>
      ),
    },
  ]

  let [status, setStatus] = useState('0')
  let [showModal, setShowModal] = useState(false)
  let [form, setForm] = useState({
    des: '',
    completeTime: '',
  })
  let [tableData, setTableData] = useState([
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
  ])
  const formIns = useRef(null)

  const handleComplete = (row, i) => {
    tableData[i].status = '1'
    tableData[i].completeTime = dayjs().format('YYYY-MM-DD HH:mm:ss')
    setTableData([...tableData])
  }
  const handleDelete = (row, i) => {
    tableData.splice(i, 1)

    setTableData([...tableData])
  }
  const handleAdd = () => {
    setShowModal(true)
  }
  const handleOk = () => {
    // 注意校验成功会直接走 then,校验不通过会走 catch
    formIns.current.validateFields().then(e => {
      const len = tableData.length
      const id = len ? (+tableData[len - 1].id + 1) : 1
      tableData.push({
        key: id,
        id: id,
        des: form.des,
        status: '2',
        completeTime: dayjs(form.completeTime).format('YYYY-MM-DD HH:mm:ss'),
      })

      setTableData([...tableData])
      handleCancel()
    }).catch(err => { console.log(err) })
  }
  const handleCancel = () => {
    setShowModal(false)
    formIns.current.resetFields()
  }

  const onChange = ({ target: { value }}) => {
    setStatus(value)
  }

  const onChangeDate = (e) => {
    setForm({
      ...form,
      completeTime: e
    })
  }
  const onChangeDes = ({ target: { value }}) => {
    setForm({
      ...form,
      des: value
    })
  }

  return (
    <>
      <div className='task'>
        <div className='task-header'>
          <h3>TASK OA 任务管理系统</h3>
          <Button type='primary' onClick={handleAdd}>新增任务</Button>
        </div>
        <div className='task-radio'>
          <Radio.Group buttonStyle="solid" onChange={onChange} value={status}>
            <Radio.Button value="0">全部</Radio.Button>
            <Radio.Button value="2">未完成</Radio.Button>
            <Radio.Button value="1">已完成</Radio.Button>
          </Radio.Group>
        </div>

        <Table columns={columns} dataSource={tableData} pagination={{ hideOnSinglePage: true }} />
      </div>
      <Modal title="新增任务" open={showModal} onOk={handleOk} onCancel={handleCancel}>
        <Form
          initialValues={form}
          labelCol={{ span: 6 }}
          ref={formIns}
        >
          <Form.Item label="任务描述" name="des" rules={[{ required: true }]}>
            <Input placeholder="请输入任务描述" vlaue={form.des} onChange={onChangeDes} />
          </Form.Item>
          <Form.Item label="预期完成时间" name="completeTime" rules={[{ required: true, type: 'date' }]}>
            <DatePicker format='YYYY-MM-DD HH:mm:ss'  showTime onChange={onChangeDate}  />
          </Form.Item>
        </Form>
      </Modal>
    </>
  )
}

export default Task
