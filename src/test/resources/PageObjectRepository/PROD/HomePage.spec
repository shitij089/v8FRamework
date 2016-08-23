
#Object Definitions
=======================================================================================================================
img_homeIcon                    xpath                     //div[@class='logo onepage logo_lg']//img                        
btn_ActiveMenuTab               xpath                     //div[@id='menu-onepage']//li[@class='active']/a[@href='#${menu name}']
btn_MenuTab                     xpath                     //div[@id='menu-onepage']//a[@href='#${menu name}']
btn_search                      id                        searchsubmit
txt_search                      css                       input[class*='field search_input']
list_allsearchedParagraph       xpath                     //p/strong
drpdwn_contactType              xpath                     //label[@class='select']/span/select
btn_contactType                 xpath                     //option[@value='${enquiry type}']
txt_contactDetails              xpath                     //label[text()='${enquiry detail type}']/..//input
txt_contactMessage              xpath                     //label[text()='Message']/..//textarea
btn_sendContactMessage          css                       #home-page-contact-form
txt_alertMessage                xpath                     //form//div[@role='alert']
img_quote                       classname                 rfq_img
btn_quote                       classname                 request_button
list_headingBlog                css                       .heading_blog>a
txt_title                       css                       .title>h1
list_videoLink                  xpath                     //a[contains(@class,'videoLink')]
btn_playVideo                   classname                 vjs-big-play-button
txt_currentTime                 classname                 vjs-current-time-display
frame_video                     classname                 cboxIframe
btn_cancel                      id                        cboxClose
img_spinner                     xpath                     //div[@class='vjs-loading-spinner']
arrow_next                      xpath                     .//*[text()='»']
frame_videoContent              id                        my-video
list_pagination                 css                       .pagination>a
btn_download                    xpath                     //button[contains(text(),'Download')]
txt_titleDownloadForm           css                       .download_form>p>b
list_readMoreLink               css                       .more-link
list_pageNumbers                css                       .page-numbers
btn_next                        xpath                     //a[contains(text(),'Next >')]
=======================================================================================================================