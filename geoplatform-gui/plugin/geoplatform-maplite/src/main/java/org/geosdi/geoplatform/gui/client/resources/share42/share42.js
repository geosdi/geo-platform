/* share42.com | 05.05.2014 | (c) Dimox */
function share42() {
    var e = document.getElementsByTagName('div');
    for (var k = 0; k < e.length; k++) {
        if (e[k].className.indexOf('share42init') != -1) {
            if (e[k].getAttribute('data-url') != -1)
                var u = e[k].getAttribute('data-url');
            if (e[k].getAttribute('data-title') != -1)
                var t = e[k].getAttribute('data-title');
            if (e[k].getAttribute('data-image') != -1)
                var i = e[k].getAttribute('data-image');
            if (e[k].getAttribute('data-description') != -1)
                var d = e[k].getAttribute('data-description');
            if (e[k].getAttribute('data-path') != -1)
                var f = e[k].getAttribute('data-path');
            if (e[k].getAttribute('data-icons-file') != -1)
                var fn = e[k].getAttribute('data-icons-file');
            if (!f) {
                function path(name) {
                    var sc = document.getElementsByTagName('script'), sr = new RegExp('^(.*/|)(' + name + ')([#?]|$)');
                    for (var p = 0, scL = sc.length; p < scL; p++) {
                        var m = String(sc[p].src).match(sr);
                        if (m) {
                            if (m[1].match(/^((https?|file)\:\/{2,}|\w:[\/\\])/))
                                return m[1];
                            if (m[1].indexOf("/") == 0)
                                return m[1];
                            b = document.getElementsByTagName('base');
                            if (b[0] && b[0].href)
                                return b[0].href + m[1];
                            else
                                return document.location.pathname.match(/(.*[\/\\])/)[0] + m[1];
                        }
                    }
                    return null;
                }
                f = path('share42.js');
            }
            if (!u)
                u = location.href;
            if (!t)
                t = document.title;
            if (!fn)
                fn = 'icons.png';
            function desc() {
                var meta = document.getElementsByTagName('meta');
                for (var m = 0; m < meta.length; m++) {
                    if (meta[m].name.toLowerCase() == 'description') {
                        return meta[m].content;
                    }
                }
                return'';
            }
            if (!d)
                d = desc();
            u = encodeURIComponent(u);
            t = encodeURIComponent(t);
            t = t.replace(/\'/g, '%27');
            i = encodeURIComponent(i);
            d = encodeURIComponent(d);
            d = d.replace(/\'/g, '%27');
            var fbQuery = 'u=' + u;
            if (i != 'null' && i != '')
                fbQuery = 's=100&p[url]=' + u + '&p[title]=' + t + '&p[summary]=' + d + '&p[images][0]=' + i;
            var vkImage = '';
            if (i != 'null' && i != '')
                vkImage = '&image=' + i;
            var s = new Array('"#" onclick="window.open(\'http://www.blogger.com/blog_this.pyra?t&u=' + u + '&n=' + t + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=550, height=440, toolbar=0, status=0\');return false" title="BlogThis!"', '"#" data-count="dlcs" onclick="window.open(\'http://delicious.com/save?url=' + u + '&title=' + t + '&note=' + d + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=710, height=660, toolbar=0, status=0\');return false" title="Save to Delicious"', '"http://digg.com/submit?url=' + u + '" title="Share on Digg"', '"https://www.evernote.com/clip.action?url=' + u + '&title=' + t + '" title="Share on Evernote"', '"#" data-count="fb" onclick="window.open(\'http://www.facebook.com/sharer.php?m2w&' + fbQuery + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=550, height=440, toolbar=0, status=0\');return false" title="Share on Facebook"', '"#" onclick="window.open(\'http://www.google.com/bookmarks/mark?op=edit&output=popup&bkmk=' + u + '&title=' + t + '&annotation=' + d + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=550, height=500, toolbar=0, status=0\');return false" title="Save to Google Bookmarks"', '"#" data-count="gplus" onclick="window.open(\'https://plus.google.com/share?url=' + u + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=550, height=440, toolbar=0, status=0\');return false" title="Share on Google+"', '"#" data-count="lnkd" onclick="window.open(\'http://www.linkedin.com/shareArticle?mini=true&url=' + u + '&title=' + t + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=600, height=400, toolbar=0, status=0\');return false" title="Share on Linkedin"', '"http://www.livejournal.com/update.bml?event=' + u + '&subject=' + t + '" title="Post to LiveJournal"', '"http://www.mister-wong.ru/index.php?action=addurl&bm_url=' + u + '&bm_description=' + t + '" title="Save to Mister Wong"', '"http://www.myspace.com/Modules/PostTo/Pages/?u=' + u + '&t=' + t + '&c=' + d + '" title="Share on MySpace"', '"#" data-count="pin" onclick="window.open(\'http://pinterest.com/pin/create/button/?url=' + u + '&media=' + i + '&description=' + t + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=600, height=300, toolbar=0, status=0\');return false" title="Pin It"', '"http://reddit.com/submit?url=' + u + '&title=' + t + '" title="Share on Reddit"', '"http://www.stumbleupon.com/submit?url=' + u + '&title=' + t + '" title="Share on StumbleUpon"', '"http://technorati.com/faves?add=' + u + '&title=' + t + '" title="Share on Technorati"', '"#" onclick="window.open(\'http://www.tumblr.com/share?v=3&u=' + u + '&t=' + t + '&s=' + d + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=450, height=440, toolbar=0, status=0\');return false" title="Share on Tumblr"', '"#" data-count="twi" onclick="window.open(\'https://twitter.com/intent/tweet?text=' + t + '&url=' + u + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=550, height=440, toolbar=0, status=0\');return false" title="Share on Twitter"', '"#" data-count="vk" onclick="window.open(\'http://vk.com/share.php?url=' + u + '&title=' + t + vkImage + '&description=' + d + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=550, height=440, toolbar=0, status=0\');return false" title="Share on VK"', '"#" onclick="window.open(\'http://bookmarks.yahoo.com/toolbar/savebm?u=' + u + '&t=' + t + '&d=' + d + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=550, height=400, toolbar=0, status=0\');return false" title="Save to Yahoo! Bookmarks"', '"" onclick="return fav(this);" title="Save to Browser Favorites"', '"#" onclick="window.open(\'mailto:?subject=MapLite sharing Link&body=' + u + '\', \'_blank\', \'scrollbars=0, resizable=1, menubar=0, left=100, top=100, width=550, height=400, toolbar=0, status=0\');return false" title="Send email"');
            var l = '';
            for (j = 0; j < s.length; j++)
                l += '<a rel="nofollow" style="display:inline-block;vertical-align:bottom;width:32px;height:32px;margin:0 6px 6px 0;padding:0;outline:none;background:url(' + f + fn + ') -' + 32 * j + 'px 0 no-repeat" href=' + s[j] + ' target="_blank"></a>';
            e[k].innerHTML = '<span id="share42">' + l + '</span>';
        }
    }
    ;
};
function fav(a) {
    var title = document.title;
    var url = document.location;
    try {
        window.external.AddFavorite(url, title);
    } catch (e) {
        try {
            window.sidebar.addPanel(title, url, '');
        } catch (e) {
            if (typeof (opera) == 'object' || window.sidebar) {
                a.rel = 'sidebar';
                a.title = title;
                a.url = url;
                a.href = url;
                return true;
            } else {
                alert('Press Ctrl-D to add the page to browser favorites');
            }
        }
    }
    return false;
}
;
window.onload = share42();