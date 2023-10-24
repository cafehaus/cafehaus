module.exports = {
  root: true,
  env: {
    node: true,
  },
  extends: [
    'plugin:vue/vue3-essential',
    '@vue/standard',
  ],
  parserOptions: {
    parser: 'babel-eslint',
  },
  rules: {
    'no-console': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'no-debugger': process.env.NODE_ENV === 'production' ? 'warn' : 'off',
    'space-before-function-paren': 'off', // 关闭函数括号前的空格验证
    quotes: ['error', 'single'], // 强制使用单引号
    semi: ['error', 'never'], // 强制结尾不使用分号
    'comma-dangle': ['error', 'always-multiline'], // 在不同的行时，要求使用拖尾逗号
  },
}
