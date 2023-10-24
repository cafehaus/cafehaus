const moment = require('moment')

exports.relativeTime = time => moment(new Data(time * 1000)).fromNow()