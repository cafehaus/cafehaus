import React from 'react'
import ReactDOM from 'react-dom/client'
import Task from './views/Task-func'
import './index.less'

import { ConfigProvider } from 'antd'
import zhCN from 'antd/locale/zh_CN'

const root = ReactDOM.createRoot(document.getElementById('root'))
root.render(
  <ConfigProvider locale={zhCN}>
    <Task />
  </ConfigProvider>
)
