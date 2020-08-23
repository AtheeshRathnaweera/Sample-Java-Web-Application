let $slider = $('.slider-item');
let currentSliderItem = 0;
let sliderInterval = setInterval(nextSlide, 3000);

let playing = true;
let $pausePlayButton = $('#pause-play');
let $nextButton = $('#next');
let $previousButton = $('#previous');

let $sliderPanel = $('.slider-panel');
let $indContainer = $('.slider-panel__navigation');
let $indItem = $('.indicator');

function nextSlide() {
    goToSlide(currentSliderItem + 1);
}

function prevSlide() {
    goToSlide(currentSliderItem - 1);
}

function goToSlide(n) {
    $($slider[currentSliderItem]).toggleClass('active');
    $($indItem[currentSliderItem]).attr('class', 'far fa-circle indicator');

    currentSliderItem = ($slider.length + n) % $slider.length;

    $($slider[currentSliderItem]).toggleClass('active');
    $($indItem[currentSliderItem]).attr('class', 'fas fa-circle indicator');
}

function pauseSlideShow() {
    $pausePlayButton.attr('class', 'far fa-play-circle');
    playing = false;
    clearInterval(sliderInterval);
}

function playSlideShow() {
    $pausePlayButton.attr('class', 'far fa-pause-circle');
    playing = true;
    sliderInterval = setInterval(nextSlide, 3000);
}

$sliderPanel.css('display', 'flex');

$pausePlayButton.on('click', () => {
    if (playing) pauseSlideShow();
    else playSlideShow();
});

$nextButton.on('click', () => {
    pauseSlideShow();
    nextSlide();
});

$previousButton.on('click', () => {
    pauseSlideShow();
    prevSlide();
});

$indContainer.on('click', (event) => {
    let target = event.target;

    if (target.classList.contains('indicator')) {
        pauseSlideShow();
        goToSlide(+target.getAttribute('data-slide-to'));
    }
});

//---------------------------------------------------------------------
$(document).on('keydown', keyNavigation);

function keyNavigation(event) {

    if (event.code === 'ArrowLeft') { //стрелка влево
        pauseSlideShow();
        prevSlide();
    }
    if (event.code === 'ArrowRight') { //стрелка вправо
        pauseSlideShow();
        nextSlide();
    }
    if (event.code === 'Space') { //пробел
        if (playing) pauseSlideShow();
        else playSlideShow();
    }
}

//---------------------------------------------------------------------