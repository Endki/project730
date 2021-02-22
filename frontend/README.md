# 프로젝트 실행하기
- `npm install`
- `npm run serve`

### 설치된 npm library
> @next: 최신 버전으로 설치하겠다는 예약어
> --save option의 뜻: [원문](https://stackoverflow.com/questions/19578796/what-is-the-save-option-for-npm-install)
Before version 5, NPM simply installed a package under node_modules by default. When you were trying to install dependencies for your app/module, you would need to first install them, and then add them (along with the appropriate version number) to the dependencies section of your package.json.
The --save option instructed NPM to include the package inside of the dependencies section of your package.json automatically, thus saving you an additional step.
- `npm install -g @vue/cli`
  - install이 안되는 경우
  1. npm config get prefix 치고 컴퓨터에 깔린 경로 확인
  2. 환경변수 - 시스템 변수 - PATH에 위에서 확인한 path 추가
  3. cmd 다시 관리자권한으로 실행하고 vue --version 치고 정상동작 확인

- `npm install vue-router@next`
- `npm install vuex@next`
- `npm install --save core-js`
- `npm install --save axios@next`
- `npm install --save mitt`
- `yarn add --dev @fullhuman/postcss-purgecss`
- `yarn add --dev cssnano`: CSS 파일 크기 압축