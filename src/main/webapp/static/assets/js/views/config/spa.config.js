$(function () {
    var static_prefix = '/static/assets/js/views';
    var url_prefix = '/rest/';
    vipspa.start({
        view: '#admin-body',
        router: {
            'home': {
                templateUrl: url_prefix + 'home',
                controller: static_prefix + '/home.js'
            },
            'class_info': {
                templateUrl: url_prefix + 'class_info/class_info',
                controller: static_prefix + '/class_info/class_info.js'
            },
            'items': {
                templateUrl: url_prefix + 'items/items',
                controller: static_prefix + '/items/items.js'
            },
            'course_modules': {
                templateUrl: url_prefix + 'course_module/course_modules',
                controller: static_prefix + '/course_module/course_modules.js'
            },
            'course_sechedule': {
                templateUrl: url_prefix + 'course_sechedule/course_sechedules',
                controller: static_prefix + '/course_sechedule/course_sechedules.js'
            },
            'tests': {
                templateUrl: url_prefix + 'tests/tests',
                controller: static_prefix + '/tests/tests.js'
            },
            'note_edit': {
                templateUrl: url_prefix + 'note/edit',
                controller: static_prefix + '/note_edit.js'
            },
            'cate': {
                templateUrl: url_prefix + 'cate',
                controller: static_prefix + '/cate.js'
            },
            'file': {
                templateUrl: url_prefix + 'file',
                controller: static_prefix + '/file.js'
            },
            'tag': {
                templateUrl: url_prefix + 'tag',
                controller: static_prefix + '/tag.js'
            },
            'about': {
                templateUrl: url_prefix + 'about',
                controller: static_prefix + '/about.js'
            },
            'keyword': {
                templateUrl: url_prefix + 'keyword',
                controller: static_prefix + '/keyword.js'
            },
            'users': {
                templateUrl: url_prefix + 'user/users',
                controller: static_prefix + '/user/user.js'
            },
            'settings': {
                templateUrl: url_prefix + 'settings',
                controller: static_prefix + '/settings.js'
            },
            'qrcode': {
                templateUrl: url_prefix + 'settings/qrcode',
                controller: static_prefix + '/qrcode.js'
            },
            'comment': {
                templateUrl: url_prefix + 'comment',
                controller: static_prefix + '/comment.js'
            },
            'message': {
                templateUrl: url_prefix + 'message',
                controller: static_prefix + '/message.js'
            },
            'noteblog': {
                templateUrl: url_prefix + 'noteblog',
                controller: static_prefix + '/noteblog.js'
            },
            'profile': {
                templateUrl: url_prefix + 'profile',
                controller: static_prefix + '/profile.js'
            },
            'defaults': 'home' //默认路由
        },
        errorTemplateId: '#error'
    });

});
