#!/bin/bash

TIME="1"
TELEGRAM_BOT_TOKEN="5635936568:AAEw_Dde-aue7Q-3OKXRVyolseALK9OBCU4"
TELEGRAM_USER_ID="1510426188"

URL="https://api.telegram.org/bot$TELEGRAM_BOT_TOKEN/sendMessage"

# Проверка значения CI_JOB_STATUS
if [ "$CI_JOB_STATUS" == "failed" ]; then
    STATUS_EMOJI="❌"
else
    STATUS_EMOJI="✅"
fi

TEXT="%0A%0AStatus: $STATUS_EMOJI $CI_JOB_STATUS%0A%0AProject: $CI_PROJECT_NAME%0AURL: $CI_PROJECT_URL/pipelines/$CI_PIPELINE_ID/%0ABranch: $CI_COMMIT_REF_SLUG%0AStage: $CI_JOB_STAGE"

curl -s --max-time $TIME -d "chat_id=$TELEGRAM_USER_ID&disable_web_page_preview=1&text=$TEXT" $URL > /dev/null

